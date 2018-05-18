package org.open.home.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.open.BaseController;
import org.open.ConfigProperties;
import org.open.model.MenuNode;
import org.open.model.MyUser;
import org.open.model.SiteMapNode;
import org.open.system.model.SysDatapermission;
import org.open.system.model.SysPermission;
import org.open.utils.ClientUtils;
import org.open.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ClientUtils clientUtils;
	
    
    private String foreBackType = "backstage";//代表后台
    
    /***
     * 首页
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home()  {
        return "home";
    }
    
    @ResponseBody()
    @RequestMapping(value="/menu", method = RequestMethod.POST)
    public String menu() throws Exception {
        List<MenuNode> menuList = new ArrayList<>();
        List<SiteMapNode> list = clientUtils.getSiteMapFromCache(foreBackType.toString()+"_SiteMap", "sitemap.xml");
        List<SiteMapNode> sbuList = clientUtils.getSubTList(list,"parentResourceId","00-00-00-00-00-00");
        if (null != sbuList && sbuList.size() > 0) {
            for (SiteMapNode siteMapNode : sbuList) {
                //访问权限控制
                if (configProperties.isLogin() && configProperties.isPermission()){
                    boolean flag = checkPermission(siteMapNode.getResourceID());
                    if (!flag)
                        continue;
                }
                MenuNode menuNode = new MenuNode();
                menuNode.setId(siteMapNode.getId());
                menuNode.setFixed(siteMapNode.isFixed());
                menuNode.setImageUrl(siteMapNode.getImageUrl());
                menuNode.setMapClass(siteMapNode.getMapClass());
                menuNode.setMenu(siteMapNode.getIsMenu());
                menuNode.setParentId(siteMapNode.getParentId());
                menuNode.setParentResourceId(siteMapNode.getParentResourceId());
                menuNode.setTarget(siteMapNode.getTarget());
                menuNode.setTitle(siteMapNode.getTitle());
                menuNode.setUrl(siteMapNode.getUrl());
                menuNode.setResourceId(siteMapNode.getResourceID());
                menuNode.setChildNodes(new ArrayList<MenuNode>());
                menuList.add(menuNode);
                //创建子menu
                loadMenuJson(menuNode,list);
            }
        }
        String json = JsonUtils.SerializeJsonByList(menuList);
        return super.SetTableDataJson(list.size(),json);
    }

    private void loadMenuJson(MenuNode menuNode,List<SiteMapNode> list){
        List<SiteMapNode> sbuList = clientUtils.getSubTList(list,"parentResourceId",menuNode.getResourceId());
        if (null != sbuList && sbuList.size() > 0) {
            for (SiteMapNode siteMapNode : sbuList) {
                //访问权限控制
                if (configProperties.isLogin() && configProperties.isPermission()){
                    boolean flag = checkPermission(siteMapNode.getResourceID());
                    if (!flag)
                        continue;
                }
                MenuNode menuNode1 = new MenuNode();
                menuNode1.setId(siteMapNode.getId());
                menuNode1.setFixed(siteMapNode.isFixed());
                menuNode1.setImageUrl(siteMapNode.getImageUrl());
                menuNode1.setMapClass(siteMapNode.getMapClass());
                menuNode1.setMenu(siteMapNode.getIsMenu());
                menuNode1.setParentId(siteMapNode.getParentId());
                menuNode1.setParentResourceId(siteMapNode.getParentResourceId());
                menuNode1.setTarget(siteMapNode.getTarget());
                menuNode1.setTitle(siteMapNode.getTitle());
                menuNode1.setUrl(siteMapNode.getUrl());
                menuNode1.setResourceId(siteMapNode.getResourceID());
                menuNode1.setChildNodes(new ArrayList<MenuNode>());
                menuNode.getChildNodes().add(menuNode1);
                loadMenuJson(menuNode1, list);
            }
            menuNode.setHasChildren(true);
        }
        else{
            menuNode.setHasChildren(false);
        }
    }
    
    
    
    /**
     * 检测是否有访问权限
     * @param resourceId
     * @return
     */
    private boolean checkPermission(String resourceId){
        MyUser<List<SysDatapermission>,List<SysPermission>> myUser = clientUtils.getUserObject(request);
        List<SysPermission> permissionList = myUser.getK();
        return clientUtils.checkSubTList(permissionList,"permissionId",resourceId);

    }
    
    
    
    
}
