package org.open;

import org.apache.commons.lang.StringUtils;
import org.open.utils.AppUtils;
import org.open.utils.LogTextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandler extends DefaultHandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {

		String result= "redirect:/error";
		ModelAndView modelAndView = new ModelAndView();
		//Url的定位
		String urlString = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		if (!StringUtils.isEmpty(queryString)) {
			urlString += "?"+queryString;
		}
        //记录异常日志
		LogTextUtils logger = LogTextUtils.getLogger();
		logger.wirteError(ex,new String[]{"\r\n错误Url:",urlString});
	     
		ex.printStackTrace();
		if (AppUtils.isAjaxRequest(request)){
			response.setStatus(1000);
			System.out.println("1000");
		}
		else{
			modelAndView.setViewName(result);
		}
		System.out.println("全局错误");	
		return modelAndView;
	}

}
