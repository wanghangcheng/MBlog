package com.SpringBoot.MBlog.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.service.AccountService;
import com.SpringBoot.MBlog.service.RegisterService;

@Controller
public class PublicController {
	
	@Autowired RegisterService _regService;
	@Autowired AccountService accountService;
	/**
	 * 注册页面生成
	 * @param mv
	 * @return
	 */
	@RequestMapping("/register.html")
	public Object register(ModelAndView mv) {
		mv.setViewName("register");
		return mv;
	}
	/**
	 * 输入信息后注册账户
	 * @param formUser
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value= {"/public/register"})
	public Object register(User formUser) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result= _regService.register(formUser);
		return result;
	}
	/**
	 * 通过用户名，密码登录
	 * @param username
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/public/login")
	public Object login(String username,String password) {
		
		return _regService.login(username, password);
	}
	/**
	 * 账户退出
	 * @param request
	 * @return
	 */
	@RequestMapping("/public/logout")
	public Object logout(HttpServletRequest request) {
		request.getSession().invalidate();//废弃现有session
		return "redirect:/login.html";
	}

	/**
	 * 忘记密码页面
	 * @param mv
	 * @return
	 */
	@RequestMapping("/public/forgot")
	public Object forgot(ModelAndView mv) {
		mv.setViewName("forgot");
		return mv;
	}
	//忘记密码并修改为新密码
	@ResponseBody
	@RequestMapping(value="/public/forgot/eidtPwd",method=RequestMethod.POST)
	public Object eidtPWd(String email,String password) {
		System.out.println(email+"----"+password);
		return accountService.editPwd(email, password);
	}
}
