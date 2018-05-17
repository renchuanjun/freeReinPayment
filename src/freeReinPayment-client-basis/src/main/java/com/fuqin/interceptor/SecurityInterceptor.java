package com.fuqin.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fuqin.config.BaseConfigProperties;
import com.fuqin.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 * Created by lenovo on 2017/5/2.
 */
public class SecurityInterceptor implements HandlerInterceptor {

    private BaseConfigProperties baseConfigProperties;

    public BaseConfigProperties getBaseConfigProperties() {
        return baseConfigProperties;
    }

    public void setBaseConfigProperties(BaseConfigProperties baseConfigProperties) {
        this.baseConfigProperties = baseConfigProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /**
         * 检查SQL注入攻击
         */
        if (baseConfigProperties.isFilter()) {
            Map<String, String[]> map = httpServletRequest.getParameterMap();
            Collection<String[]> values = map.values();
            Iterator<String[]> iterator = values.iterator();
            while (iterator.hasNext()) {
                String[] items = iterator.next();
                for (String item : items) {
                    String[] filter = baseConfigProperties.getInputFilter().split("\\|");
                    String[] val = item.toLowerCase().split(" ");
                    List<String> list = Arrays.asList(filter);
                    for (String string : val) {
                        if (list.contains(string)) {
                            System.out.println("存在SQL注入的关键字["+string+"]");
                            if (AppUtils.isAjaxRequest(httpServletRequest)){
                                httpServletResponse.setStatus(999);//代表SQL注入
                            }
                            else{
                                httpServletResponse.sendRedirect(AppUtils.getVirtualPath(httpServletRequest)+"/errorsql?val="+string);
                            }

                            return false;
                          }

                    }
                }
            }

        }

        /**
         * 检查特殊字符攻击
         */
        if (baseConfigProperties.isFilterChar()) {
            Map<String, String[]> map = httpServletRequest.getParameterMap();
            Set<Map.Entry<String,String[]>> entrySet = map.entrySet();
            Iterator<Map.Entry<String, String[]>> iterator = entrySet.iterator();

            String[] filter = baseConfigProperties.getInputFilterChar().split("\\|");
			/*String[] copyOf = Arrays.copyOf(filter, filter.length+1);
			copyOf[copyOf.length-1] = "|";
			List<String> list = Arrays.asList(copyOf);*/
            List<String> list = Arrays.asList(filter);
            String[] nameKeyFilter = baseConfigProperties.getRequestNameKey().split("\\|");
            List<String> list2 = Arrays.asList(nameKeyFilter);
            String[] likeNameKeyFilter = baseConfigProperties.getRequestLikeNameKey().split("\\|");
            List<String> list3 = Arrays.asList(likeNameKeyFilter);

            while (iterator.hasNext()) {
                //过滤掉不需要过滤的key
                boolean flag =false;
                Map.Entry<String, String[]> entry = iterator.next();
                String key = entry.getKey();
                String[] value = entry.getValue();
                if (list2.contains(key)) {
                    continue;
                }
                else {
                    //过滤掉不需要过滤的模糊key
                    for (String string : list3) {
                        if (key.indexOf(string) != -1) {
                            flag = true;
                            break;
                        }
                    }
                }

                if (flag) {
                    continue;
                }

                //进行特殊字符的检查
                for (String stringVal : value) {
                    for (String strFilter : list) {
                        if (stringVal.toLowerCase().indexOf(strFilter) != -1) {
                            System.out.println("存在特殊字符注入的键["+key+"],值["+stringVal+"]");
                            if (AppUtils.isAjaxRequest(httpServletRequest)) {
                                httpServletResponse.setStatus(997);//代表特殊字符注入
                            }
                            else {
                                httpServletResponse.sendRedirect(AppUtils.getVirtualPath(httpServletRequest)+"/errorchar?key=" + key + "&val=" + stringVal);
                            }
                            return false;
                        }

                    }
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
