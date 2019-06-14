package com.unifs.behavioranalysis.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * @author 张恭雨
 * @date
 * @param  
 * @return 
 * 功能描述:  安全策略过滤器
 */
@WebFilter(filterName = "securityFilter",urlPatterns = {"/*"})
public class SecurityFilter implements Filter {
	private Logger log=LoggerFactory.getLogger(SecurityFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //log.info("--经过安全策略过滤器--");
		response.setHeader("Content-Security-Policy","default-src 'self';" +
				"script-src 'self' 'unsafe-inline' 'unsafe-eval';" +
				"object-src 'self';" +
				"style-src 'self' 'unsafe-inline';"+
				"img-src 'self'");

		response.setHeader("X-XSS-Protection","1; mode=block");
		response.setHeader("X-Content-Type-Options","nosniff");
		//调用下一个过滤器（这是过滤器工作原理，不用动）
        chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
