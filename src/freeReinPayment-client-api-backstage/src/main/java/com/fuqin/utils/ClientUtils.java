package com.fuqin.utils;



import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fuqin.ConfigProperties;
import com.fuqin.model.ButtonNode;
import com.fuqin.model.MyUser;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.SiteMapNode;
import com.fuqin.utils.BaseClientUtils;
import com.fuqin.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by lenovo on 2017/4/13.
 */
@Component
public class ClientUtils extends BaseClientUtils{

	@Autowired
	private ConfigProperties configProperties;
	
	@Autowired
	private RedisUtils redisUtils;
	
    @Autowired
    private ResourceLoader resourceLoader;
	/***
     * 登录时生成session
     * @param request
     * @param myUser
     */
    public void setUserObject(HttpServletRequest request,MyUser myUser){
        HttpSession session = request.getSession(true);
        String key = configProperties.getKeyPrefix()+"_Back_User";
        session.setAttribute(key,myUser);
    }

    /***
     * 获取session所记录的myUser对象
     * @param request
     * @return
     */
    public MyUser getUserObject(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        String key = configProperties.getKeyPrefix()+"_Back_User";
        Object object = session.getAttribute(key);
        if (null != object) {
            MyUser myUser = (MyUser)object;
            return myUser;
        }
        return null;
    }

    /**
     * 获取当前登录人的主键UserId
     * @param request
     * @return
     */
    public String getCurrentUserId(HttpServletRequest request) {
        MyUser myUser = getUserObject(request);
        if (null != myUser) {
            return myUser.getUserId();
        }
        return configProperties.getKeyPrefix()+"_987654321";
    }

    /**
     * 获取当前登录人的帐号
     * @param request
     * @return
     */
    public String getCurrentAccount(HttpServletRequest request) {
        MyUser myUser = getUserObject(request);
        if (null != myUser) {
            return myUser.getUserName();
        }
        return configProperties.getKeyPrefix()+"_hna_user";
    }

    /**
     * 获取当前登录人的姓名
     * @param request
     * @return
     */
    public String getCurrentName(HttpServletRequest request) {
        MyUser myUser = getUserObject(request);
        if (null != myUser) {
            return myUser.getName();
        }
        return configProperties.getKeyPrefix()+"_测试帐号";
    }
	
	/**
	 * 对实体类转JSON进行非空判断
	 * @param object
	 * @return
	 */
	public static String transforJson(Object object){
		String dataJson = new String("{}");
		if(null != object){
			dataJson = JsonUtils.SerializeJson(object);
		}
		return dataJson;
	}
	
	/**
	 * 对List转JSON进行非空判断
	 * @param objectList
	 * @return
	 */
	public static  String transforJsonByList(Object objectList){
		String dataJson = new String("[]");
		if(null != objectList){
			dataJson = JsonUtils.SerializeJsonByList(objectList);
		}
		return dataJson;
	}
	
	/**
	 * 对Map转JSON进行非空判断
	 * @param objectMap
	 * @return
	 */
	public static <V, K> String transforJsonByMap(Map<K,V> objectMap){
		String dataJson = new String("{}");
		if(null != objectMap){
			dataJson = JsonUtils.SerializeJsonByMap(objectMap);
		}
		return dataJson;
	}

	public static PagerAndOrderByArgs getPagerAndOrderByArgs(HttpServletRequest request) {
		PagerAndOrderByArgs pagerArgs = new PagerAndOrderByArgs();

		if (!StringUtils.isEmpty(request.getParameter("pageSize")))
			pagerArgs.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		
		// 分页
		if (!StringUtils.isEmpty(request.getParameter("page")))
			pagerArgs.setCurrentPage(Integer.parseInt(request
					.getParameter("page")));

		if (!StringUtils.isEmpty(request.getParameter("pageNumber")))
			pagerArgs.setPageIndex(Integer.parseInt(request
					.getParameter("pageNumber")));

		if (!StringUtils.isEmpty(request.getParameter("rows")))
			pagerArgs
					.setPageSize(Integer.parseInt(request.getParameter("rows")));

		// 字段排序
		if (!StringUtils.isEmpty(request.getParameter("sort"))) {
			String str = request.getParameter("sort");
			pagerArgs.setSortColumn(str);
		}

		if (!StringUtils.isEmpty(request.getParameter("order")))
			pagerArgs
					.setSortOrderBy("asc".equals(request.getParameter("order")) ? true
							: false);
		return pagerArgs;
	}

	/***
     * 获取sitemap的缓存
     * @param cacheKey
     * @param sitemap
     * @return
     * @throws Exception
     */
    public List<SiteMapNode> getSiteMapFromCache(String cacheKey, String sitemap) throws Exception{
        ArrayList<SiteMapNode> list = new ArrayList<SiteMapNode>();
        Object object = null;//application.getAttribute("SiteMap");//采用缓存
        cacheKey = configProperties.getKeyPrefix() + "_" + cacheKey;
        if (configProperties.isSiteMapNode()) {
            object =  redisUtils.get(cacheKey);//采用缓存
        }

        if (null != object) {
            list = (ArrayList<SiteMapNode>)object;
        }
        else
        {
            //获取xml的流对象
            Resource resource = resourceLoader.getResource("classpath:"+sitemap);
            InputStream inputStream = resource.getInputStream();
            //加载xml成为dom对象
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(configProperties.isXmlValidating());
            DocumentBuilder db=factory.newDocumentBuilder();
            Document xmldoc=db.parse(inputStream);
            //定位根节点
            Element root=xmldoc.getDocumentElement();
            //查找全部的节点
            NodeList nodeList = XmlDomUtils.selectNodes("//siteMapNode", root);
            if (null == nodeList) return list;
            int count = nodeList.getLength();
            for (int i = 0; i < count; i++) {
                if (!(nodeList.item(i) instanceof Element)) continue;
                Element element = (Element)nodeList.item(i);
                SiteMapNode siteMapNode = convertSiteMapNode(element);
                list.add(siteMapNode);
            }
            //存为全局缓存
            //使用缓存存值
            if (configProperties.isSiteMapNode()) {
                if (null != list && list.size() > 0) {
                    redisUtils.put(cacheKey, list,3600000L);
                }
            }

        }
        return list;
    }
    /**
     * XML的节点转换为List的节点集合
     * @param element
     * @return
     */
    private SiteMapNode convertSiteMapNode(Element element){
        SiteMapNode siteMapNode = new SiteMapNode();
        boolean fixed = StringUtils.isEmpty(element.getAttribute("fixed")) ? false : Boolean.parseBoolean(element.getAttribute("fixed"));
        String id = element.getAttribute("id");
        String imageUrl = element.getAttribute("imageUrl");
        Boolean isMenu = StringUtils.isEmpty(element.getAttribute("ismenu")) ? false : Boolean.parseBoolean(element.getAttribute("ismenu"));
        String parentId = null;
        String parentResourceId = null;
        //获取父节点的Id与resourceID
        Node parentNode = element.getParentNode();
        if (null != parentNode && parentNode instanceof Element) {
            Element parentElement = (Element)parentNode;
            parentId = parentElement.getAttribute("id");
            parentResourceId = parentElement.getAttribute("resourceID");
        }
        String resourceID = element.getAttribute("resourceID");
        String target = element.getAttribute("target");
        String title = element.getAttribute("title");
        String url = element.getAttribute("url");
        String mapClass = element.getAttribute("mapClass");
        siteMapNode.setMapClass(mapClass);
        siteMapNode.setFixed(fixed);
        siteMapNode.setId(id);
        siteMapNode.setImageUrl(imageUrl);
        siteMapNode.setIsMenu(isMenu);
        siteMapNode.setParentId(parentId);
        siteMapNode.setParentResourceId(parentResourceId);
        siteMapNode.setResourceID(resourceID);
        siteMapNode.setTarget(target);
        siteMapNode.setTitle(title);
        siteMapNode.setUrl(url);

        return siteMapNode;
    }

    /***
     * 获取Button的缓存
     * @param cacheKey
     * @param buttons
     * @return
     * @throws Exception
     */
    public List<ButtonNode>  getButtonsFromCache(String cacheKey, String buttons) throws Exception{
        ArrayList<ButtonNode> list = new ArrayList<ButtonNode>();
        Object object = null;//application.getAttribute("Buttons");//采用缓存
        cacheKey = configProperties.getKeyPrefix() + "_" + cacheKey;
        if (configProperties.isButtonNode()) {
            object =  redisUtils.get(cacheKey);//采用缓存
        }
        if (null != object) {
            list = (ArrayList<ButtonNode>)object;
        }
        else{
            //获取xml的流对象
            Resource resource = resourceLoader.getResource("classpath:"+buttons);
            InputStream inputStream = resource.getInputStream();
            //加载xml成为dom对象
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(false);
            factory.setValidating(configProperties.isXmlValidating());
            DocumentBuilder db=factory.newDocumentBuilder();
            Document xmldoc=db.parse(inputStream);
            //定位根节点
            Element root=xmldoc.getDocumentElement();
            //查找引用的节点，进行替换
            NodeList nodeList = XmlDomUtils.selectNodes("//root/references", root);
            if (null == nodeList) return list;
            int count = nodeList.getLength();
            for (int i = 0; i < count; i++) {
                if (!(nodeList.item(i) instanceof Element)) continue;
                Element element = (Element)nodeList.item(i);
                String id = element.getAttribute("id");
                String path = element.getAttribute("path");
                //在主xml上创建一个节点
                Element elementSubRoot = xmldoc.createElement(id);
                root.replaceChild(elementSubRoot, element);//进行新旧节点替换
                //读取子xml进行合并操作
                Resource resource1 = resourceLoader.getResource("classpath:buttons/"+path);
                InputStream inputStream1 = resource1.getInputStream();
                //加载xml成为dom对象
                DocumentBuilderFactory factory1=DocumentBuilderFactory.newInstance();
                factory1.setIgnoringElementContentWhitespace(true);
                factory1.setValidating(configProperties.isXmlValidating());
                DocumentBuilder db1=factory1.newDocumentBuilder();
                Document xmldoc1=db1.parse(inputStream1);
                //定位子xml的根节点
                Element root1=xmldoc1.getDocumentElement();
                NodeList nodeList1 = XmlDomUtils.selectNodes("//"+id+"/page", root1);
                if (null == nodeList1) continue;
                int count1 = nodeList1.getLength();
                for (int j = 0; j < count1; j++) {
                    if (!(nodeList1.item(j) instanceof Element)) continue;
                    Element element3 = (Element)nodeList1.item(j);
                    //page节点
                    Element elementPage = xmldoc.createElement("page");
                    elementPage.setAttribute("key", element3.getAttribute("key"));
                    elementPage.setAttribute("url", element3.getAttribute("url"));
                    elementPage.setAttribute("titile", element3.getAttribute("titile"));
                    elementPage.setAttribute("pageResourceID", element3.getAttribute("pageResourceID"));
                    elementSubRoot.appendChild(elementPage);
                    NodeList nodeList2 = element3.getChildNodes();
                    if (null == nodeList2) continue;
                    int count2 = nodeList2.getLength();
                    for (int k = 0; k < count2; k++) {
                        if (!(nodeList2.item(k) instanceof Element)) continue;
                        Element element4 = (Element)nodeList2.item(k);
                        //group节点
                        Element elementGroup = xmldoc.createElement("group");
                        elementGroup.setAttribute("groupid", element4.getAttribute("groupid"));
                        elementGroup.setAttribute("title", element4.getAttribute("title"));
                        elementPage.appendChild(elementGroup);
                        NodeList nodeList3 = element4.getChildNodes();
                        if (null == nodeList3) continue;
                        int count3 = nodeList3.getLength();
                        for (int m = 0; m < count3; m++) {
                            if (!(nodeList3.item(m) instanceof Element)) continue;
                            Element element5 = (Element)nodeList3.item(m);
                            //Item节点
                            Element elementItem = xmldoc.createElement("item");
                            elementItem.setAttribute("id", element5.getAttribute("id"));
                            elementItem.setAttribute("title", element5.getAttribute("title"));
                            elementItem.setAttribute("btnID", element5.getAttribute("btnID"));
                            elementItem.setAttribute("resourceID", element5.getAttribute("resourceID"));
                            elementItem.setAttribute("fixed", element5.getAttribute("fixed"));
                            elementItem.setAttribute("enable", element5.getAttribute("enable"));
                            elementItem.setAttribute("onClientClick", element5.getAttribute("onClientClick"));
                            elementGroup.appendChild(elementItem);
                        }
                    }
                }
            }
            //XmlDomUtils.output(root);
            //遍历全部的Page，生成实体集合
            //查找全部的节点
            NodeList nodeList0 = XmlDomUtils.selectNodes("//root/*/page/group/item", root);
            if (null == nodeList0) return list;
            int count0 = nodeList0.getLength();
            for (int i = 0; i < count0; i++) {
                Element element = (Element)nodeList0.item(i);
                ButtonNode buttonNode = convertButtonNode(element);
                list.add(buttonNode);
            }

            //存为全局缓存
            //使用缓存存值
            //application.setAttribute("Buttons", list);
            if (configProperties.isButtonNode()) {
                if (null != list && list.size() > 0) {
                    redisUtils.put(cacheKey, list,3600000L);
                }
            }
        }

        return list;
    }
    /**
     * Button的XML的节点转换为List的节点集合
     * @param element
     * @return
     */
    private ButtonNode convertButtonNode(Element element){
        ButtonNode buttonNode = new ButtonNode();
        //item
        if (null == element) return buttonNode;
        String id = element.getAttribute("id");
        String btnId = element.getAttribute("btnID");
        String enable = element.getAttribute("enable");
        String fixed = element.getAttribute("fixed");
        String onClientClick = element.getAttribute("onClientClick");
        String resourceId = element.getAttribute("resourceID");
        String title = element.getAttribute("title");

        buttonNode.setBtnId(btnId);
        buttonNode.setEnable(enable);
        buttonNode.setFixed(fixed);
        buttonNode.setId(id);
        buttonNode.setOnClientClick(onClientClick);
        buttonNode.setResourceId(resourceId);
        buttonNode.setTitle(title);


        String groupId = null;
        String groupTitle = null;
        String key = null;
        String pageResourceId = null;
        String pageTitle = null;

        //group
        Node parentNodeGroup = element.getParentNode();
        if (null == parentNodeGroup) return buttonNode;

        Element elementGroup = (Element)parentNodeGroup;
        groupId = elementGroup.getAttribute("groupid");
        groupTitle = elementGroup.getAttribute("title");
        buttonNode.setGroupId(groupId);
        buttonNode.setGroupTitle(groupTitle);

        //page
        Node parentNodePage = elementGroup.getParentNode();
        if (null == parentNodePage) return buttonNode;

        Element elementPage = (Element)parentNodePage;
        key = elementPage.getAttribute("key");
        pageResourceId = elementPage.getAttribute("pageResourceID");
        pageTitle = elementPage.getAttribute("titile");
        buttonNode.setPageResourceId(pageResourceId);
        buttonNode.setKey(key);
        buttonNode.setPageTitle(pageTitle);

        return buttonNode;
    }
    
    /***
     * 获取集合子数据
     * @param list
     * @param propertyName
     * @param k
     * @param <T>
     * @param <K>
     * @return
     */
    public <T,K> List<T>  getSubTList(List<T> list,String propertyName,K k){
        EqualPredicate parameter = new EqualPredicate(k);
        BeanPredicate tableCoulmn_paramerter = new BeanPredicate(propertyName, parameter);
        Predicate[] allPredicateArray = {tableCoulmn_paramerter };
        Predicate allPredicate = PredicateUtils.allPredicate(allPredicateArray);
        return (List<T>) CollectionUtils.select(list, allPredicate);
    }
	
    /***
     * 检测集合是否存在数据
     * @param list
     * @param propertyName
     * @param k
     * @param <T>
     * @param <K>
     * @return
     */
    public <T,K> boolean  checkSubTList(List<T> list,String propertyName,K k){
        EqualPredicate parameter = new EqualPredicate(k);
        BeanPredicate tableCoulmn_paramerter = new BeanPredicate(propertyName, parameter);
        Predicate[] allPredicateArray = {tableCoulmn_paramerter };
        Predicate allPredicate = PredicateUtils.allPredicate(allPredicateArray);
        return CollectionUtils.exists(list, allPredicate);
    }
    
	/**
	 * 针对分页拼接成easyui列表对应的json串
	 * @param total
	 * @param json
	 * @return
	 */
	public static String SetTableDataJson(int total, String json) {
		String str = "{\"total\":" + total + ",\"rows\":" + json + "}";
		return str;
	}
	
	
}
