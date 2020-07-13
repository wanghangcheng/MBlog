package com.SpringBoot.MBlog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
import com.SpringBoot.MBlog.dao.FavorDao;
import com.SpringBoot.MBlog.dao.FollowsDao;
import com.SpringBoot.MBlog.dao.NotifyDao;
import com.SpringBoot.MBlog.dao.UserDao;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.Comment;
import com.SpringBoot.MBlog.entity.Favor;
import com.SpringBoot.MBlog.entity.Follows;
import com.SpringBoot.MBlog.entity.Notify;
import com.SpringBoot.MBlog.entity.Tag;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.service.HomeService;
import com.SpringBoot.MBlog.util.EhcacheUtil;
import com.SpringBoot.MBlog.util.LoginUtil;
import com.SpringBoot.MBlog.vo.ArticleVo;
import com.SpringBoot.MBlog.vo.UserVo;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired FollowsDao followsDao;
	@Autowired ArticleDao articleDao;
	@Autowired CommentDao commtentDao;
	@Autowired NotifyDao notifyDao;
	@Autowired FavorDao favorDao;
	@Autowired UserDao userDao;
	@Autowired ArticleTagDao articleTagDao;
	
	@Override
	//我的关注
	public void follows(Integer page,ModelAndView mv) {
	
		
		Sort sort = new Sort(Direction.DESC,"created");
		
		User loginUser = LoginUtil.getLoginUser();
		Pageable pageable = new PageRequest(page-1, 5,sort);
		Page<Follows> data = followsDao.findFollows(loginUser.getId(),pageable);
		
		List<UserVo> list = new ArrayList<UserVo>();
		for(Follows fo:data.getContent()) {
			
			User user = fo.getTarget();
			UserVo userVo = new UserVo();
			BeanUtils.copyProperties(user, userVo);
			
			//查询用户发表的文章数和评论数
			int articleCount = articleDao.countByUser(user.getId());
			int commtentCount = commtentDao.countByUser(user.getId());
			
			userVo.setArticleCount(articleCount);
			userVo.setCommtentCount(commtentCount);
			list.add(userVo);
		}
		
		mv.addObject("pageInfo", data);//分页数据
		mv.addObject("voList",list);//userVo列表
	}
	//我的粉丝
	@Override
	public void fans(Integer page, ModelAndView mv) {
		
		Sort sort = new Sort(Direction.DESC,"created");
		User loginUser = LoginUtil.getLoginUser();
		Pageable pageable = new PageRequest(page-1, 5,sort);
		Page<Follows> data = followsDao.findFans(loginUser.getId(),pageable);
		
		List<UserVo> list = new ArrayList<UserVo>();
		for(Follows fo:data.getContent()) {
			
			User user = fo.getSource();//粉丝
			UserVo userVo = new UserVo();
			BeanUtils.copyProperties(user, userVo);
			
			//查询用户发表的文章数和评论数
			int articleCount = articleDao.countByUser(user.getId());
			int commtentCount = commtentDao.countByUser(user.getId());
			
			userVo.setArticleCount(articleCount);
			userVo.setCommtentCount(commtentCount);
			list.add(userVo);
		}
		
		mv.addObject("pageInfo", data);//分页数据
		mv.addObject("voList",list);//userVo列表
	}
	//通知
	@Override
	public void notifies(Integer page, ModelAndView mv) {
		
		Sort sort = new Sort(Direction.DESC,"created");
		User loginUser = LoginUtil.getLoginUser();
		Pageable pageable = new PageRequest(page-1, 4,sort);
		Page<Notify> data = notifyDao.findByUserStaus(loginUser.getId(), 0, pageable);
		
		mv.addObject("pageInfo", data);
	}
	//我的喜欢
	public void favors(Integer page, ModelAndView mv) {
		
		Sort sort = new Sort(Direction.DESC,"created");
		User loginUser = LoginUtil.getLoginUser();
		Pageable pageable = new PageRequest(page-1, 3,sort);
		Page<Favor> data = favorDao.favorByUserId(loginUser.getId(),pageable);
		
		List<Favor> list = new ArrayList<Favor>();
		for(int i=0;i<data.getContent().size();i++) {
			
			Favor favor =  data.getContent().get(i);
			Article a = data.getContent().get(i).getArticle();
			ArticleVo vo = new ArticleVo();
			BeanUtils.copyProperties(a, vo);//将vo对象替换掉a对象,增加\属性
			list.add(favor);
			
			//查询评论数，设置10秒缓存
			String key = "a_comment_"+a.getId();
			Integer count = EhcacheUtil.get("mytest", key);
			
			if(count==null) {
				count = commtentDao.countByArticleId(a.getId());//通过文章id查询评论数
				EhcacheUtil.put("mytest", key, count,10,10);
			}
			
			vo.setComment(count);
			
			//查询喜欢数，设置10秒缓存
			String key2="a_like"+a.getId();
			Integer favorCount = EhcacheUtil.get("mytest", key2);
			if(favorCount==null) {
				favorCount = favorDao.countByArticle(a.getId());//通过文章id查询喜欢数
				EhcacheUtil.put("mytest", key2, favorCount,10,10);
			}
			vo.setLike(favorCount);
			
			favor.setUser(a.getUser());
			favor.setArticle(vo);
		}
		mv.addObject("pageInfo",data);
		mv.addObject("favorList",list);
	}
	//我的评论
	@Override
	public void comments(Integer page, ModelAndView mv) {
		
		Sort sort = new Sort(Direction.DESC,"created");
		User loginUser = LoginUtil.getLoginUser();
		Pageable pageable = new PageRequest(page-1, 2,sort);
		Page<Comment> data = commtentDao.findByUserId(loginUser.getId(), pageable);
		
		List<Comment> list = new ArrayList<Comment>();
		for(int i=0;i<data.getContent().size();i++) {
			
			Comment c =  data.getContent().get(i);
			Article a = data.getContent().get(i).getArticle();
			ArticleVo aVo = new ArticleVo();
			BeanUtils.copyProperties(a, aVo);//将vo对象替换掉a对象,增加\属性
			list.add(c);
			
			//查询评论数，设置10秒缓存
			String key = "a_comment_"+a.getId();
			Integer count = EhcacheUtil.get("mytest", key);
			
			if(count==null) {
				count = commtentDao.countByArticleId(a.getId());//通过文章id查询评论数
				EhcacheUtil.put("mytest", key, count,10,10);
			}
			
			aVo.setComment(count);
			
			//查询喜欢数，设置10秒缓存
			String key2="a_like"+a.getId();
			Integer favorCount = EhcacheUtil.get("mytest", key2);
			if(favorCount==null) {
				favorCount = favorDao.countByArticle(a.getId());//通过文章id查询喜欢数
				EhcacheUtil.put("mytest", key2, favorCount,10,10);
			}
			aVo.setLike(favorCount);
			c.setArticle(aVo);
		}
		mv.addObject("pageInfo",data);
		mv.addObject("commentList",list);
	}
	//动态
	public void dynamic(Integer page, ModelAndView mv) {
		
		Sort sort = new Sort(Direction.DESC,"created");
		User loginUser = LoginUtil.getLoginUser();
		Pageable pageable = new PageRequest(page-1, 4,sort);
		Page<Notify> data = notifyDao.findByUserStaus(loginUser.getId(), 1, pageable);
		
		mv.addObject("pageInfo", data);
	}
	//我的文章
	@Override
	public void posts(Integer page, ModelAndView mv) {
		
		Sort sort = new Sort(Direction.DESC,"createTime");
		User loginUser = LoginUtil.getLoginUser();
		Pageable pageable = new PageRequest(page-1, 3,sort);
		Page<Article> data = articleDao.findByUserId(loginUser.getId(), pageable);
		mv.addObject("pageInfo",data);
		
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
	
}
