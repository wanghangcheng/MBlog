package com.SpringBoot.MBlog.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.service.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired HomeService homeService;
	//我的主页
	@RequestMapping(value= {"","/","/index"})
	public Object index(@RequestParam(defaultValue="1")Integer page,HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("home/index");
		homeService.dynamic(page,mv);
		return mv;
	}
	//我的关注页面
	@RequestMapping(value= {"/follows"})
	public Object follows(@RequestParam(defaultValue="1") Integer page,HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("home/follows");
		homeService.follows(page,mv);
		return mv;
	}
	//我的粉丝页面
	@RequestMapping(value= {"/fans"})
	public Object fans(@RequestParam(defaultValue="1") Integer page,HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("home/fans");
		homeService.fans(page,mv);
		return mv;
	}
	//通知页面
	@RequestMapping(value= {"/notifies"})
	public Object notifies(@RequestParam(defaultValue="1") Integer page,HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("home/notifies");
		homeService.notifies(page,mv);
		return mv;
	}
	//我的喜欢（文章收藏）页面
	@RequestMapping(value= {"/favors"})
	public Object favors(@RequestParam(defaultValue="1") Integer page,HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("home/favors");
		homeService.favors(page,mv);
		return mv;
	}
	//我的评论页面
	@RequestMapping(value= {"/comments"})
	public Object comments(@RequestParam(defaultValue="1") Integer page,HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("home/comments");
		homeService.comments(page,mv);
		return mv;
	}
	//我的文章页面
	@RequestMapping(value= {"/posts"})
	public Object posts(@RequestParam(defaultValue="1") Integer page,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("home/posts");
		homeService.posts(page,mv);
		return mv;
	}
	
}
