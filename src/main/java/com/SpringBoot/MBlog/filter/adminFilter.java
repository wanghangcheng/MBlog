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

@WebFilter (urlPatterns= {"/admin/*"})

public class adminFilter  implements Filter{
	/*
	 * 路径拦截：判断是否有用户登录没有返回登录页，有用户登录判断他的角色权限是什么，不是管理员不允许访问admin下路径
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		
		HttpSession session = request2.getSession();
		User user = (User) session.getAttribute("loginInfo");
		
		//System.out.println(user.getPower().getRole());
		if(user==null) {
			response2.sendRedirect("/login.html");
			return ;
		}
		if(user.getPower().getRole()!=2) {
			response2.sendRedirect("/home/index.html");
			return ;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
