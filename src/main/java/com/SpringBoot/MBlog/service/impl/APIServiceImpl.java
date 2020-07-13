package com.SpringBoot.MBlog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.MBlog.Admin.mapper.ArticleMapper;
import com.SpringBoot.MBlog.Admin.mapper.UserMapper;
import com.SpringBoot.MBlog.dao.ArticleDao;
import com.SpringBoot.MBlog.dao.UserDao;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.service.APIService;
import com.SpringBoot.MBlog.util.EhcacheUtil;

@Service
public class APIServiceImpl implements APIService {
	
	@Autowired ArticleDao articleDao;
	@Autowired UserDao userDao;
	@Autowired UserMapper userMapper;
	@Autowired ArticleMapper articleMapper;
	
	@Override
	public List<User> hotusers(int maxSize) {
		
		List<User> data = EhcacheUtil.get("Cache1", "hotusers");
		if(data==null||data.isEmpty()) {
			//System.out.println("未发现缓存，发起sql");
//			Sort sort = new Sort(Direction.DESC,"createTime");
//			Pageable pageable = new PageRequest(0,maxSize, sort );
//			Page<User> pageUser = userDao.findAll(pageable);
//			data = pageUser.getContent();
			data = userMapper.ApiFindUserAll(maxSize);
			
			EhcacheUtil.put("Cache1", "hotusers", data,30,30);
		}
		return data;
	}

	@Override
	public List<Article> latests(int maxSize) {
		//先查询缓存中是否有对应数据
		//存在直接调用，不存在进行sql查询
		//缓存设置超时时间
		//缓存不能影响原有功能
		
		List<Article> data = EhcacheUtil.get("Cache1", "article_latests");
		
		if(data==null||data.isEmpty()) {
//			Sort sort = new Sort(Direction.DESC,"createTime");
//			Pageable pageable = new PageRequest(0,maxSize, sort );
//			Page<Article> pageArticle = articleDao.findAll(pageable);
			data = articleMapper.ApiFindArtByTime(maxSize);
			EhcacheUtil.put("Cache1", "article_latests", data,10,10);
		}
		return data;
	}

	@Override
	public List<Article> hots(int maxSize) {
		
		List<Article> data = EhcacheUtil.get("Cache1", "article_hots");
		if(data==null||data.isEmpty()) {
//			Sort sort = new Sort(Direction.DESC,"hits");
//			Pageable pageable = new PageRequest(0,maxSize, sort );
//			Page<Article> pageArticle = articleDao.findAll(pageable);
			data = articleMapper.ApiFindArtByHit(maxSize);
			EhcacheUtil.put("Cache1", "article_hots", data,60,60);
		}
		return data;
	}

}
