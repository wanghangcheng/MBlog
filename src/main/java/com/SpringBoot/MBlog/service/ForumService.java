package com.SpringBoot.MBlog.service;

import org.springframework.web.servlet.ModelAndView;

/**
 * 板块业务
 * @author hangcheng
 *
 */
public interface ForumService {
	
	void findForumArtives(String forumCode,String sortType,int page, ModelAndView mv);

	void findHomeArt(ModelAndView mv);
	
}
