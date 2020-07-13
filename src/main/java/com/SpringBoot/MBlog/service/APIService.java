package com.SpringBoot.MBlog.service;

import java.util.List;

import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.User;

public interface APIService {
	
	/**
	 * 最新用户
	 * @param maxSize
	 * @return
	 */
	List<User> hotusers(int maxSize);
	/**
	 * 最新发布的文章
	 * @param maxSize
	 * @return
	 */
	List<Article> latests(int maxSize);
	/**
	 * 点击量最高的文章
	 * @param maxSize
	 * @return
	 */
	List<Article> hots(int maxSize);
}
