package com.SpringBoot.MBlog.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.SpringBoot.MBlog.dao.ArticleDao;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.Forum;
import com.SpringBoot.MBlog.entity.User;
/**
 * 使用Jsoup爬取网站内容
 * @author hangcheng
 * @version 1.0
 */
public class TestJsoup {
	public static void main(String[] args) throws IOException, ParseException {
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/ApplicationContext.xml");
		ArticleDao articleDao = context.getBean(ArticleDao.class);
		
		Forum forum = new Forum();
		//文章所属版块
		forum.setId(3L);
		//文章所属用户
		User user = new User();
		user.setId(2L);
		
		for(int i=0;i<=5;i++) {
			Document document = Jsoup.connect("https://bbs.csdn.net/forums/Java?page="+i).get();
//			Element table = document.getElementsByClass("forums_tab_table").first();
			Elements elements = document.getElementsByClass("forums_title ");
		
			for(Element element:elements) {
				//文章内容链接
				String url = "https://bbs.csdn.net"+element.attr("href");
				
				Document doc2 = Jsoup.connect(url).get();
				
				//获取文章页面内容
				Element storey = doc2.getElementsByClass("mod_topic_wrap").first();
				//获取文章的时间
				Element timer = storey.getElementsByClass("date_time").first();
				//获取文章的点击数
				String hots = storey.getElementsByClass("reply_num").first().text();
				
				Element e2 = doc2.getElementsByClass("post_body").first();
//				System.out.println(e2.html());
				
				//设置时间格式
				SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				Article article = new Article();
				e2.getElementsByTag("script").remove();
				article.setContent(e2.html());
				article.setCreateTime(sFormat.parse(timer.text()));
				article.setHits(Long.parseLong(hots));
				article.setTitle(element.text());
				article.setForum(forum);
				article.setUser(user);
				
				articleDao.save(article);
				
			}
		}
	}

}
