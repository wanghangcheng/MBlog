package com.SpringBoot.MBlog.service;

import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.vo.Result;


public interface TaService {
	/**
	 * user基信息，user的文章分页
	 * @param userId
	 * @param page
	 * @return {user,page<Article>}
	 */
	void findTaData(long userId,int page,ModelAndView mv);
	/**
	 * 投诉用户功能
	 * @param userId
	 * @param genre
	 * @param remarks
	 * @return
	 */
	Result ComplaintUser(Long userId,Integer genre,String remarks);
}
