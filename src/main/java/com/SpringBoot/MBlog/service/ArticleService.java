package com.SpringBoot.MBlog.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.vo.Result;

public interface ArticleService {
	
	Result save(String title,long group,String content,String tags,HttpServletRequest request);

	void view(long articleId,ModelAndView mv,HttpServletRequest request);
	
	Article getArticleById(long articleId);

	Result delete(long id);

	void to_update(long articleId, ModelAndView mv);

	void update(long articleId, String title, String content, long group, String tags,ModelAndView mv);

	void tag(String tags, int page,ModelAndView mv);	
	
	Result ArtComplaint(Long artId,Integer genre,String remarks);

}
