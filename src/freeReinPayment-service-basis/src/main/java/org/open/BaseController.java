package org.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lenovo on 2017/4/10.
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;



/*    protected <T extends HNAResult<K>,K> boolean validate(T t){
        Boolean rsaTimeOutStatus = null;
        Boolean rsaSignStatus = null;
        Boolean rsaFieldNull = null;
        if (null != request.getAttribute("rsaFieldNull") && (Boolean)request.getAttribute("rsaFieldNull")){
            //RSA的必填项存在空值
            t.setDetailInfo("sign,timestamp,module都不能为空");
            return true;
        }
        if (null != request.getAttribute("rsaTimeOutStatus")){
            rsaTimeOutStatus = (Boolean)request.getAttribute("rsaTimeOutStatus");

            if (rsaTimeOutStatus){
                //时间戳过期，该请求失效
                t.setDetailInfo("时间戳过期，该请求无效");
                return true;
            }
        }

        if (null != request.getAttribute("rsaSignStatus")){
            rsaSignStatus = (Boolean)request.getAttribute("rsaSignStatus");

            if (!rsaSignStatus){
                t.setDetailInfo("签名验证失败");
                return true;
            }
        }

        return false;
    }*/
}
