package com.SpringBoot.MBlog.service.impl;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.SpringBoot.MBlog.dao.ArticleDao;
import com.SpringBoot.MBlog.dao.CommentDao;
import com.SpringBoot.MBlog.dao.NotifyDao;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.Comment;
import com.SpringBoot.MBlog.entity.Notify;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.service.CommentService;
import com.SpringBoot.MBlog.util.LoginUtil;
import com.SpringBoot.MBlog.vo.Result;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired CommentDao commentDao;
	@Autowired NotifyDao notifyDao;
	@Autowired ArticleDao articleDao;
	
	/**
	 * 清除缓存
	 */
	@Override
	@CacheEvict(value="CommentCount",key="#p0") 
	public Result save(Long articleId, Long targetCommentId, String text) {
		
		User loginUser =LoginUtil.getLoginUser();
		
		if(loginUser==null) {
			return Result.of(0, "未登录");
		}
		
		Comment comment = new Comment();
		Article article = articleDao.getOne(articleId);
		Comment target = null;
		if(targetCommentId!=0) {
			target = new Comment();
			target.setId(targetCommentId);
		}
		
		System.out.println(targetCommentId+"----"+target);
		
		comment.setArticle(article);
		comment.setAuthor(loginUser);
		comment.setContent(text);
		comment.setCreated(new Date());
		comment.setTarget(target);
		
		commentDao.save(comment);
		
		//给文章作者发通知
		
		Notify notify = new Notify();
		notify.setAvatar(loginUser.getAvatar());
		notify.setCreated(new Date());
		notify.setTitle(loginUser.getNickname());
		notify.setUser(article.getUser());
		notify.setUrl("/ta/"+loginUser.getId());
		String s2 = String.format("评论了你的文章 - <a href = \"/view/%s.html\">点击查看详情</a>", article.getId());//格式化字符串
		notify.setContent(s2);
		notify.setStaus(0);
		notifyDao.save(notify);
		
		
		return Result.of(1, "发表评论成功");
	}

	@Override
	public Result list(Long articleId, int pageSize, int pn) {
		Sort sort = new Sort(Direction.DESC,"created");//通过created降序
		Pageable pageable = new PageRequest(pn-1, pageSize,sort);
		Page<Comment> data = commentDao.findByArticleId(articleId, pageable);
		return Result.of(1, "成功", data);
	}

	@Override
	public Result delete(long id) {
		User loginUser = LoginUtil.getLoginUser();
		if(loginUser==null) {
			return Result.of(0, "未登录");
		}
		
		Comment Comment = commentDao.find(loginUser.getId(), id);
		commentDao.delete(Comment);
		return Result.of(1, "删除评论成功");
	}
	
}
