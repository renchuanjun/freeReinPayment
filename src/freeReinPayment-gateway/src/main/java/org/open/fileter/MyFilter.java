package org.open.fileter;


import java.io.InputStream;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(MyFilter.class);
	
	@Override
	public Object run() {
		try {
			RequestContext ctx = RequestContext.getCurrentContext();  
	        HttpServletRequest request = ctx.getRequest();
	        System.out.println(String.format("%s AccessUserNameFilter request to %s", request.getMethod(), request.getRequestURL().toString()));
	        String username = request.getParameter("name");
	        InputStream in = ctx.getRequest().getInputStream();
	        String body = StreamUtils.copyToString(in, Charset.forName("UTF-8")); 
	        ctx.setRequest(new HttpServletRequestWrapper(request) {
//	        	@Override  
//	            public ServletInputStream getInputStream() throws IOException {  
//	              return new ServletInputStreamWrapper(reqBodyBytes);  
//	            }  
//	            @Override  
//	            public int getContentLength() {  
//	              return reqBodyBytes.length;  
//	            }  
//	            @Override  
//	            public long getContentLengthLong() {  
//	              return reqBodyBytes.length;  
//	            }  
	        });
		} catch (Exception e) {
			// TODO: handle exception
		}
        return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		 return "pre";
	}
	/**
	 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下： 
		pre：路由之前
		routing：路由之时
		post： 路由之后
		error：发送错误调用
		filterOrder：过滤的顺序
		shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
		run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
	 */
}