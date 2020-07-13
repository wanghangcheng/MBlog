package com.SpringBoot.MBlog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.service.TaService;
import com.SpringBoot.MBlog.vo.Result;

@Controller
public class TaController {
	
	@Autowired TaService taService;
	/**
	 * 用户详情页
	 * @param userId
	 * @param page
	 * @return
	 */
	@RequestMapping("/ta/{userId}")
	public Object f1(@PathVariable long userId,@RequestParam(defaultValue="1")Integer page) {
		ModelAndView mv = new ModelAndView("ta/index");
		taService.findTaData(userId, page,mv);
		return mv;
	}
	/**
	 * 投诉用户
	 * @param userId
	 * @param type
	 * @param remarks
	 * @return
	 */
	@PostMapping("/ta/complaint")
	@ResponseBody
	public Result ComplaintUser(String userId,String type,String remarks) {
		return taService.ComplaintUser(Long.valueOf(userId), Integer.valueOf(type), remarks);
	}
}
