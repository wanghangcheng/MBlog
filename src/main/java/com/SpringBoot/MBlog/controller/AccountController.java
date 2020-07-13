package com.SpringBoot.MBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.service.AccountService;
import com.SpringBoot.MBlog.service.EmailService;
import com.SpringBoot.MBlog.vo.Result;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired AccountService accountService;
	@Autowired EmailService emailService;
	
	/**
	 * 登录验证
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/{name}",method=RequestMethod.GET)
	public Object index(@PathVariable String name,HttpServletRequest request) {
		
		ModelAndView mv=new ModelAndView("account/"+name);
		return mv;
	}
	/**
	 * 用户修改密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/password/change")
	public Object changePassword(String oldPassword,String password) {
		return accountService.updatePassword(oldPassword, password);
	}
	/**
	 * 用户修改基本信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/profile/change")
	public Object changeProfile(String nickname,String sign) {
		return accountService.updateProfile(nickname,sign);
	}
	/**
	 * 更新头像
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param path
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/avatar",method=RequestMethod.POST)
	public Object updateAvatar(int x,int y,int width,int height,String path ,HttpServletRequest request) {
		Result r = accountService.updateAvatar(x, y, width, height, path, request);
		int code = (int)r.get("code");
		if(code==1) {
			return "redirect:/login.html";
		}
		if(code==2) {
			return "redirect:/account/avatar";
		}
		return "redirect:/500.html";
	}
	//喜欢某个文章
	@ResponseBody
	@RequestMapping(value="/favor",method=RequestMethod.GET)
	public Object favor(long id) {
		
		return accountService.saveFavor(id);
	}
	//关注某个人，要求已登录
	@ResponseBody
	@RequestMapping(value="/follow",method=RequestMethod.GET)
	public Object follow(long id,HttpServletRequest request) {
		return accountService.saveFollow(id);
	}
	//检查是否关注了某个人
	@ResponseBody
	@RequestMapping(value="/follow/check/{userId}",method=RequestMethod.GET)
	public Object followCheck(@PathVariable Long userId,HttpServletRequest request) {
//		System.out.println("----"+userId);
		return accountService.followCheck(userId);
	}
	
	//取消关注某个人，要求已登录
	@ResponseBody
	@RequestMapping(value="/unfollow")
	public Object unfollow(long id,HttpServletRequest request) {
		return accountService.unFollow(id);
	}
	
	//取消喜欢哪篇文章，要求已登录
	@ResponseBody
	@RequestMapping(value="/unfavor")
	public Object unfavor(long id,HttpServletRequest request) {
		return accountService.unfavor(id);
	}
	//相关验证码发送
	@ResponseBody
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Object editSendEmail(@RequestParam String email) {
		System.out.println(email);
		return emailService.sendEmailVerCode(email);
	}
	//修改更换邮箱
	@ResponseBody
	@RequestMapping(value="/editEmail",method=RequestMethod.POST)
	public Object editEmail(String email) {
		return accountService.editEmail(email);
	}
}
