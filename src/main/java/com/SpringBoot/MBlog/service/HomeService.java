package com.SpringBoot.MBlog.service;

import org.springframework.web.servlet.ModelAndView;

public interface HomeService {

	void follows(Integer page,ModelAndView mv);

	void fans(Integer page, ModelAndView mv);

	void notifies(Integer page, ModelAndView mv);

	void favors(Integer page, ModelAndView mv);

	void comments(Integer page, ModelAndView mv);
	
	void dynamic(Integer page, ModelAndView mv);

	void posts(Integer page, ModelAndView mv);

}
