package com.SpringBoot.MBlog.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.SpringBoot.MBlog.dao.ForumDao;
import com.SpringBoot.MBlog.entity.Forum;
/**
 * 监听器
 * 监听一旦项目启动就查询板块信息，存储到application
 * @author 95298
 *
 */
@WebListener
public class MyListener implements ServletContextListener {
	
	@Autowired ForumDao forumDao;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/*
		 * 在不使用注解时，获取spring容器，并获取里面的dao bean
		 * ApplicationContext context = SpringContext.getApplicationContext();
		 * ForumDao groupDao = context.getBean(ForumDao.class);
		 * System.out.println("-----"+groupDao);
		 * 
		 * example查询方法：probe含有对应字段的实例对象，ExampleMatcher携带有关如何匹配特定字段的详细信息，由Probe和ExampleMatcher组成，用于查询
		 * 缺陷：属性不支持嵌套或者分组约束
		 * 
		 * Group probe = new Group();
		 * probe.setStatus(true);//基本查询基础
		 * ExampleMatcher matcher = ExampleMatcher.matching();
		 * Example<Group> example = Example.of(probe, matcher);
		 * List<Group> list = groupDao.findAll(example);
		 */
		//通过权重排序
		Sort sort = new Sort(Direction.ASC,"weight");
		Pageable pageable = new PageRequest(0, 8,sort);
		Page<Forum> Pagelist= forumDao.findByStatus(true,pageable);
		for(Forum g:Pagelist.getContent()) {
			System.out.println(g.getName());
		}
		//获取application并存储信息
		sce.getServletContext().setAttribute("FORUM_LIST", Pagelist.getContent());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
