package com.SpringBoot.MBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.MBlog.service.APIService;

@RestController//相当于Controller和ResponseBody的合体
@RequestMapping("/api")
public class APIController {
	
	@Autowired APIService apiService;
	
	//粉丝最多的用户（粉丝功能未做,用最新用户代替）
	@RequestMapping("/hotusers")
	public Object f1(int maxResults) {
		//引入缓存，半分钟一查询
		return apiService.hotusers(maxResults);
	}
	
	//最新发布的文章
	@RequestMapping("/latests")
	public Object f2(int maxResults) {
		//引入缓存，10秒一查询
//		System.out.println(maxResults);
		return apiService.latests(maxResults);
	}
	
	//点击量最高的文章
	@RequestMapping("/hots")
	public Object f3(int maxResults) {
		//引入缓存，1分钟一查询
		return apiService.hots(maxResults);
	}
	
	//测试
	@RequestMapping("/1111")
	public Object f4() {
		System.out.println("路径拦截测试");
		return null;
	}
}
