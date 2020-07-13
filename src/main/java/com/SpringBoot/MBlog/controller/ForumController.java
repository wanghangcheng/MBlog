package com.SpringBoot.MBlog.controller;
/**
 * 路径访问不同版块
 * @param request
 * @return
 */

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.service.ForumService;

@Controller
public class ForumController {
	
	@Autowired ForumService forumservice;
	
	@RequestMapping("/forum/{code}")
	public Object group(@PathVariable String code,String ord,@RequestParam(defaultValue="1") int page,HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("forum");
		forumservice.findForumArtives(code,ord,page,mv);
		return mv;
	}
	//首页应急方案
	@RequestMapping(value = {"","/","/index"})
	public Object index() {
		ModelAndView mv = new ModelAndView("home");
		forumservice.findHomeArt(mv);
		return mv;
		//return "redirect:/forum/blog";
	}
}
