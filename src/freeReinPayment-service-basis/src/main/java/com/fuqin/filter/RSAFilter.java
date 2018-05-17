package com.fuqin.filter;


import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import com.fuqin.ConfigProperties;
import com.fuqin.basis.BodyReaderHttpServletRequestWrapper;
import com.fuqin.utils.AppUtils;
import com.fuqin.utils.Base64Utils;
import com.fuqin.utils.RsaHelper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Calendar;

/**
 * Created by lenovo on 2017/4/17.
 */
public class RSAFilter implements Filter {

    private ConfigProperties configProperties;
    private ResourceLoader resourceLoader;
    public ConfigProperties getConfigProperties() {
        return configProperties;
    }

    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (configProperties.isRsa()){
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

            String url = httpServletRequest.getRequestURL().toString();
            System.out.println("RSAFilter的url:"+url);

            //判断必填字段
            String sign = httpServletRequest.getHeader("sign");
            String timestamp = httpServletRequest.getHeader("timestamp");
            String module = httpServletRequest.getHeader("module");
            if (StringUtils.isEmpty(sign) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(module)){

                httpServletRequest.setAttribute("rsaFieldNull",true);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }


            String method = httpServletRequest.getMethod();
            ServletRequest requestWrapper = null;
            String paramData = null;
            if ("GET".equals(method)){
                //暂时没管虚拟路径的问题
                requestWrapper = httpServletRequest;
                paramData = httpServletRequest.getRequestURI();
                //paramData = url.replace(httpServletRequest.getContextPath(), "");
            }
            else if ("POST".equals(method)){
                requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
                paramData = BodyReaderHttpServletRequestWrapper.getBodyString(requestWrapper);
            }

            System.out.println("paramData:"+paramData);
            System.out.println("过滤器启动");
            boolean flag = true;//状态位


            //验证时间戳的有效性
            if (configProperties.isRsaTimestamp()){
                //客户端传过来的时间戳
                long clientTimestamp = Long.valueOf(timestamp);
                //当前时间戳
                long serverTimestamp = Calendar.getInstance().getTimeInMillis();

                long time = serverTimestamp - clientTimestamp;
                System.out.println("time:"+time);
                long timeOutTimestamp = configProperties.getRsaTimeOutTimestamp();
                System.out.println("timeOutTimestamp:"+timeOutTimestamp);
                if (time > timeOutTimestamp){
                    //超过有效时间
                    requestWrapper.setAttribute("rsaTimeOutStatus",true);
                    flag = false;
                }
                else{
                    requestWrapper.setAttribute("rsaTimeOutStatus",false);
                }
            }

            if (flag){
                System.out.println("sign:"+sign);

                String pubKeyXml = AppUtils.getFileContent(resourceLoader,"classpath:public_"+module+".xml");
                PublicKey pubKey = null;
                byte[] bySign = null;
                boolean isMatch = false;
                byte[] byBaramData = null;
                try {
                    pubKey = RsaHelper.decodePublicKeyFromXml(pubKeyXml);
                    bySign = Base64Utils.decode(sign);
                    byBaramData = (paramData+timestamp).getBytes("utf-8");
                    isMatch = RsaHelper.verifySign(byBaramData,bySign, pubKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (isMatch){
                    System.out.println("签名验证成功");
                    requestWrapper.setAttribute("rsaSignStatus",true);
                }
                else{
                    System.out.println("签名验证失败");
                    requestWrapper.setAttribute("rsaSignStatus",false);
                }
            }

            filterChain.doFilter(requestWrapper, servletResponse);
        }
        else{
            System.out.println("签名验证关闭");
            servletRequest.setAttribute("rsaSignStatus",true);
            servletRequest.setAttribute("rsaTimeOutStatus",false);
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}
