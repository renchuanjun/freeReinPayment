package com.fuqin.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fuqin.config.BaseConfigProperties;
import com.fuqin.utils.AppUtils;
import com.fuqin.utils.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lenovo on 2017/4/27.
 */
@Component
public class BaseClientUtils {

    @Autowired
    private BaseConfigProperties baseConfigProperties;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ResourceLoader resourceLoader;

    /***
     * 上传时验证token值
     * @param request
     * @param response
     * @param tokenVal
     * @throws Exception
     */
    public void checkToken(HttpServletRequest request,HttpServletResponse response,String tokenVal) throws Exception{
        if (baseConfigProperties.isLogin() && baseConfigProperties.isValidateCSRF()) {

            if (StringUtils.isEmpty(tokenVal)){
                response.sendRedirect(AppUtils.getVirtualPath(request)+"/errorcsrf");
                return;
            }
            else{
                byte[] by = Base64Utils.decode(tokenVal);
                String tokenStr = new String(by,"UTF-8");
                HttpSession session = request.getSession(true);
                String token1 = session.getId();
                if (!token1.equals(tokenStr)) {
                    response.sendRedirect(AppUtils.getVirtualPath(request)+"/errorcsrf");
                    return;
                }
            }
        }

    }
    
}
