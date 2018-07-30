package org.open.fileter;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

@Component
public class MyFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(MyFilter.class);
	
	@Override
	public Object run() {
		try {
			RequestContext context = getCurrentContext();
			InputStream in = (InputStream) context.get("requestEntity");
			if (in == null) {
				in = context.getRequest().getInputStream();
			}
			String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
			System.out.println(body);
			body = body.replace("张三","李四");
			System.out.println(body);
			byte[] bytes = body.getBytes("UTF-8");
			context.setRequest(new HttpServletRequestWrapper(getCurrentContext().getRequest()) {
				@Override
				public ServletInputStream getInputStream() throws IOException {
					return new ServletInputStreamWrapper(bytes);
				}

				@Override
				public int getContentLength() {
					return bytes.length;
				}

				@Override
				public long getContentLengthLong() {
					return bytes.length;
				}
			});
		}
			catch (IOException e) {
			rethrowRuntimeException(e);
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
		return -1;
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