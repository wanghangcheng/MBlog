package com.SpringBoot.MBlog.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.SpringBoot.MBlog.dao.ArticleDao;
import com.SpringBoot.MBlog.dao.ForumDao;
import com.SpringBoot.MBlog.dao.UserDao;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.Forum;
import com.SpringBoot.MBlog.entity.User;

public class TestSave {
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/ApplicationContext.xml");
		UserDao userDao = context.getBean(UserDao.class);
		ForumDao forumDao = context.getBean(ForumDao.class);
		ArticleDao articleDao = context.getBean(ArticleDao.class);
		Date date = new Date();
		long time = date.getTime();
		
		User user = userDao.findById((long)1);
		Forum forum = forumDao.findById((long)1);
		
		for(int i=1;i<=100;i++) {
			Article article = new Article();
			article.setCreateTime(new Date(time));	
			System.out.println(user.toString());
			System.out.println(forum.toString());
			article.setForum(forum);
			article.setUser(user);
			article.setTitle("标题"+i);
			article.setContent("第"+i+"条文章");
			article.setHits((long)(Math.random()*1000));
			System.out.println(article.toString());
			articleDao.save(article);
			time+=1000;
		}
	
	}

}
