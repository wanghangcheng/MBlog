package com.SpringBoot.MBlog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SpringBoot.MBlog.entity.User;

@WebFilter(urlPatterns= {"/account/*","/home/*","/admin/*"})
/**
 * 路径拦截
 * 访问/account/*和/home/,/admin/路径内的内容时先判断是否登录，如果未登记则直接跳转登录页面，禁止访问
 * @author hangcheng
 * @version 1.0
 */
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		
		HttpSession session = request2.getSession();
		User user = (User) session.getAttribute("loginInfo");
		if(user==null) {
			response2.sendRedirect("/login.html");
			return ;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
