package com.fuqin;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.collections.functors.EqualPredicate;
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
    

    protected String outJsonStringSuccess() {
        return "success";
    }

    protected String outJsonStringFail() {
        return "fail";
    }

    protected String outJsonStringFail(String msg) {
        return "fail:" + msg;
    }

    protected String outJsonStringValidateFail() {
        return "validatefail";
    }

    /***
     * 验证form实体
     * @param result
     * @return
     */
    protected String formValidate(BindingResult result) {
        List<FieldError> list = result.getFieldErrors();
        for (FieldError fieldError : list) {
            System.out.println(fieldError.getField());
            System.out.println(fieldError.getDefaultMessage());
        }
        return outJsonStringValidateFail();

    }

    /**
     * 针对分页拼接成列表对应的json串
     *
     * @param total
     * @param json
     * @return
     */
    protected String SetTableDataJson(int total, String json) {
        String str = "{\"total\":" + total + ",\"rows\":" + json + "}";
        return str;
    }
    
  
}
