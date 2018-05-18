package org.open.controller;

import org.open.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class ErrorController extends BaseController{
	
	
	/**
	 * 返回参数解密错误
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/errordecrypt", method = RequestMethod.GET)
	public String errorDecrypt(){
		System.out.println("1q2");
		return "";
	}
	
	
}
