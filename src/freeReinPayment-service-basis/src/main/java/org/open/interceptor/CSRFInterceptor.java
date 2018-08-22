package org.open.interceptor;

import org.apache.commons.lang.StringUtils;
import org.open.config.BaseConfigProperties;
import org.open.utils.AppUtils;
import org.open.utils.Base64Utils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.UUID;

/**
 * Created by lenovo on 2017/5/2.
 */
public class CSRFInterceptor implements HandlerInterceptor {

	private BaseConfigProperties baseConfigProperties;

	public BaseConfigProperties getBaseConfigProperties() {
		return baseConfigProperties;
	}

	public void setBaseConfigProperties(
			BaseConfigProperties baseConfigProperties) {
		this.baseConfigProperties = baseConfigProperties;
	}

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object o) throws Exception {
		if (baseConfigProperties.isValidateCSRF()) {
			String method = httpServletRequest.getMethod();
			if ("POST".equals(method)) {
				String token = "";
				// 解决上传操作，获取不到token值的问题
				String formdata = "multipart/form-data";
				String header = httpServletRequest.getHeader("Content-Type");
				if (StringUtils.isNotEmpty(header)
						&& header.indexOf(formdata) > -1) {
					return true;
				} else {
					token = httpServletRequest.getParameter("token");
				}

				// System.out.println("token:"+token);
				String url = httpServletRequest.getRequestURL().toString();
				System.out.println(url);
				/*if (StringUtils.isEmpty(token)) {
					if (AppUtils.isAjaxRequest(httpServletRequest)) {
						httpServletResponse.setStatus(995);// 代表CSRF攻击
					} else {

						httpServletResponse.sendRedirect(AppUtils.getVirtualPath(httpServletRequest)+ "/errorcsrf");
					}
					System.out.println("Token不能为空!");
					return false;
				} else {
					byte[] by = Base64Utils.decode(token);
					String tokenStr = new String(by, "UTF-8");
					HttpSession session = httpServletRequest.getSession(true);
					String token1 = session.getId();
					// System.out.println("token1:"+token1);
					// System.out.println("token:"+token);
					if (!token1.equals(tokenStr)) {
						if (AppUtils.isAjaxRequest(httpServletRequest)) {
							httpServletResponse.setStatus(995);// 代表CSRF攻击
						} else {
							httpServletResponse.sendRedirect(AppUtils.getVirtualPath(httpServletRequest)+ "/errorcsrf");
						}
						System.out.println("存在CSRF攻击!");
						return false;
					}
				}*/

			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

		String method = httpServletRequest.getMethod();
		HttpSession session = httpServletRequest.getSession(true);
		if ("GET".equals(method)
				|| ("POST".equals(method) && !AppUtils
						.isAjaxRequest(httpServletRequest))) {
			String uuid = session.getId();
			String tokenval = Base64Utils.encode(uuid.getBytes("UTF-8"));
			// session.setAttribute("token", uuid);
			httpServletRequest.setAttribute("tokenVal", tokenval);
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object o, Exception e)
			throws Exception {

	}
}
