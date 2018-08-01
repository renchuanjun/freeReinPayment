package org.open.interceptor;

import org.open.BasisConfigProperties;
import org.open.utils.LogTextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lenovo on 2017/4/13.
 */
public class RSAHandlerInterceptor implements HandlerInterceptor {


    private BasisConfigProperties configProperties;

    public void setConfigProperties(BasisConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    /**
     * controller 执行之前调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String url = httpServletRequest.getRequestURL().toString();
        System.out.println("RSAHandlerInterceptor的url:"+url);
/*        if (o instanceof HandlerMethod){
            System.out.println("xxxxxxxxxxxxxx");
            HandlerMethod method = (HandlerMethod)o;
            System.out.println(method.getReturnType());

        }*/
        System.out.println("执行之前");

        if (configProperties.isRsa()){
            LogTextUtils logger = LogTextUtils.getLogger();
            Boolean rsaTimeOutStatus = null;
            Boolean rsaSignStatus = null;
            Boolean rsaFieldNull = null;
            if (null != httpServletRequest.getAttribute("rsaFieldNull") && (Boolean)httpServletRequest.getAttribute("rsaFieldNull")){
                //RSA的必填项存在空值
                logger.wirteError("sign,timestamp,module都不能为空");
                return false;
            }
            if (null != httpServletRequest.getAttribute("rsaTimeOutStatus")){
                rsaTimeOutStatus = (Boolean)httpServletRequest.getAttribute("rsaTimeOutStatus");

                if (rsaTimeOutStatus){
                    //时间戳过期，该请求失效
                    logger.wirteError("时间戳过期，该请求无效");
                    return false;
                }
            }

            if (null != httpServletRequest.getAttribute("rsaSignStatus")){
                rsaSignStatus = (Boolean)httpServletRequest.getAttribute("rsaSignStatus");

                if (!rsaSignStatus){
                    logger.wirteError("签名验证失败");
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * * controller 执行之后，且页面渲染之前调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("执行之后");
    }

    /***
     * 页面渲染之后调用，一般用于资源清理操作
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("------afterCompletion-----");
    }

}
