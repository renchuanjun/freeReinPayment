package org.open.interceptor;

import org.apache.commons.lang.StringUtils;
import org.open.ConfigProperties;
import org.open.model.ButtonNode;
import org.open.model.FQResult;
import org.open.model.MyUser;
import org.open.model.SiteMapNode;
import org.open.system.model.SysDatapermission;
import org.open.system.model.SysPermission;
import org.open.utils.AppUtils;
import org.open.utils.ClientUtils;
import org.open.utils.JsonUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/5/2.
 */
public class AuthorityInterceptor implements HandlerInterceptor {

    private ConfigProperties configProperties;
    
    private ClientUtils clientUtils;
    
    

    public ConfigProperties getConfigProperties() {
        return configProperties;
    }

    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    public ClientUtils getClientUtils() {
		return clientUtils;
	}

	public void setClientUtils(ClientUtils clientUtils) {
		this.clientUtils = clientUtils;
	}

	@Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

		String url = httpServletRequest.getRequestURI();
        if (configProperties.isLogin()){

            HttpSession session = httpServletRequest.getSession(true);
            String key = configProperties.getKeyPrefix()+"_Back_User";
            Object object = session.getAttribute(key);

            if (null == object) {
                if (AppUtils.isAjaxRequest(httpServletRequest)) {
                    url = httpServletRequest.getRequestURL().toString();
                    System.out.println(url);
                    httpServletResponse.setStatus(998);//代表无权限
                    return false;
                } else {
                    url = httpServletRequest.getRequestURL().toString();
                    String queryString = httpServletRequest.getQueryString();
                    if (!StringUtils.isEmpty(queryString)) {
                        url += "?" + queryString;
                    }
                    //httpServletRequest.setAttribute("returnUrl", url);
                    httpServletResponse.sendRedirect(AppUtils.getVirtualPath(httpServletRequest)+"/login?returnUrl="+ AppUtils.urlEncode(url));
                    return false;
                }
            }
        }
        if ("GET".equals(httpServletRequest.getMethod())&& !AppUtils.isAjaxRequest(httpServletRequest)) {
            String queryString = httpServletRequest.getQueryString();
            if (!StringUtils.isEmpty(httpServletRequest.getContextPath())) {
                url = url.replaceAll(httpServletRequest.getContextPath(), "");
            }
            // 注isQuery是解决某些url不需要全匹配的方式,false为不需要全匹配
            if (!StringUtils.isEmpty(queryString)
                    && !"false".equals(httpServletRequest.getParameter("isQuery"))) {
                url = url + "?" + queryString;
            }
            // 通过url查找resourceID
            List<SiteMapNode> list = clientUtils.getSiteMapFromCache(0+"_SiteMap", "sitemap.xml");
            List<SiteMapNode> list2 = clientUtils.getSubTList(list,"url",url);
            if (null != list2 && list2.size() == 1) {
                // 去检查是否可以访问(1模块2页面3按钮)
                SiteMapNode siteMapNode = list2.get(0);
                String resourceId = siteMapNode.getResourceID();
                // 查找页面的操作权限
                List<ButtonNode> list3 = clientUtils.getButtonsFromCache(0+"Buttons", "buttons/button.xml");
                List<ButtonNode> list4 = clientUtils.getSubTList(list3,"pageResourceId",resourceId);
                // 访问权限开关(必须登录后方才有权限开关)
                if(configProperties.isLogin() && configProperties.isPermission()){
	                MyUser<List<SysDatapermission>,List<SysPermission>> myUser = clientUtils.getUserObject(httpServletRequest);
	                List<SysPermission> permissionList = myUser.getK();//获取到权限列表
	                boolean flag = clientUtils.checkSubTList(permissionList,"permissionId",resourceId);
	                // 说明存在页面访问权限
	                if (flag) {
	                    List<ButtonNode> list5 = new ArrayList<ButtonNode>();
	                    if (null != list4 && list4.size() > 0) {
	                        // 查询可用的按钮
	                        for (ButtonNode buttonNode : list4) {
	                            boolean flag1 = clientUtils.checkSubTList(permissionList,"permissionId",buttonNode.getResourceId());
	                            if (flag1) {//有权限的按钮返回到前台页面，用于添加事件
	                                list5.add(buttonNode);
	                            }
	                        }
	                        String operateButton = JsonUtils.SerializeJsonByList(list5);
	                        httpServletRequest.setAttribute("operateButton", operateButton);
	                    }
	                } else {
	                    // 不存在则禁止访问
	                    httpServletResponse.sendRedirect(AppUtils.getVirtualPath(httpServletRequest)+"/nopermission?returnUrl="+ AppUtils.urlEncode(url));
	                    return false;
	                }
                }else{
                	String operateButton = JsonUtils.SerializeJsonByList(list4);
                    httpServletRequest.setAttribute("operateButton", operateButton);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    } 
}
