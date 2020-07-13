package com.SpringBoot.MBlog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.dao.ArticleDao;
import com.SpringBoot.MBlog.dao.ArticleTagDao;
import com.SpringBoot.MBlog.dao.CommentDao;
import com.SpringBoot.MBlog.dao.ComplaintDao;
import com.SpringBoot.MBlog.dao.FavorDao;
import com.SpringBoot.MBlog.dao.UserDao;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.Complaint;
import com.SpringBoot.MBlog.entity.Tag;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.service.TaService;
import com.SpringBoot.MBlog.vo.Result;


@Service
public class TaServiceImpl implements TaService {

	@Autowired UserDao userDao;
	@Autowired ArticleDao articleDao;
	@Autowired CommentDao commtentDao;
	@Autowired FavorDao favorDao;
	@Autowired ArticleTagDao articleTagDao;
	@Autowired ComplaintDao complaintDao;
	
	/**
	 * 个人主页信息
	 */
	@Override
	public void findTaData(long userId, int page,ModelAndView mv) {
		
		User user = userDao.findById(userId);
		mv.addObject("user",user); 
		
		Sort sort = new Sort(Direction.DESC,"createTime");
		Pageable pageable = new PageRequest(page-1, 6,sort); 
		
		Page<Article> data = articleDao.findByUserId(userId, pageable);
		mv.addObject("pageInfo", data);
		data.getContent().forEach(act->{
			String str = delHtmlTag(act.getContent());
			if(str.length()>200) {
				str = str.substring(0,200);
			}
			else {
				str = str.substring(0,str.length());
			}
			act.setContent(str);
			//查询喜欢数，设置10秒缓存
			int favorCount = favorDao.countByArticle(act.getId());//通过文章id查询喜欢数
			int count = commtentDao.countByArticleId(act.getId());//通过文章id查询评论数
			act.setComment(count);
			act.setLike(favorCount);
			//文章标签
			List<Tag> tags = articleTagDao.findTagsByArticle(act.getId());
			act.setTags(new ArrayList<String>());
			tags.forEach(tag->{
				act.getTags().add(tag.getName());
			});
		}); 
	}
	public static String delHtmlTag(String str) {
		
		String newstr = "";
		newstr = str.replaceAll("<[.[^>]]*>", "");
		newstr = newstr.replaceAll(" ", "");
		return newstr;
	}
	/**
	 * 投诉用户
	 */
	@Override
	public Result ComplaintUser(Long userId, Integer genre, String remarks) {
		
		Complaint complaint = new Complaint();
		complaint.setTarget_user(userDao.getOne(userId));
		complaint.setGenre(genre);//投诉类别
		complaint.setCreated(new Date());
		complaint.setRemarks(remarks);
		complaint.setType(0);//投诉类型：1：文章，0：用户
		complaint.setHandle(false);//未处理
		complaintDao.save(complaint);
		return Result.of(101, "投诉成功");
	}
}
