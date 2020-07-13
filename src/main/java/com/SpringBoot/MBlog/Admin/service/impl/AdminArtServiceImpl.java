package com.SpringBoot.MBlog.Admin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.MBlog.Admin.mapper.ArticleMapper;
import com.SpringBoot.MBlog.Admin.mapper.JournalMapper;
import com.SpringBoot.MBlog.Admin.mapper.NotifyMapper;
import com.SpringBoot.MBlog.Admin.mapper.UserMapper;
import com.SpringBoot.MBlog.Admin.service.AdminArtService;
import com.SpringBoot.MBlog.entity.Admin_Journal;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.Notify;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.util.LoginUtil;
import com.SpringBoot.MBlog.vo.Result;
import com.github.pagehelper.PageHelper;

@Service
public class AdminArtServiceImpl implements AdminArtService {
	
	@Autowired ArticleMapper articleMapper;
	@Autowired UserMapper userMapper;
	@Autowired NotifyMapper notifyMapper;
	@Autowired JournalMapper journalMapper;
	
	/**
	 * 获取所有文章列表，可根据不同条件查询获取，默认都为空
	 */
	@Override
	public List<Article> findArticles(int pageNumber,int pageSize,String sort,String nickname,String artTitle,String beginDateTime,String endDateTime) {
		PageHelper.startPage(pageNumber, pageSize, sort);
		List<Article> list = articleMapper.findArticles(nickname,artTitle,beginDateTime,endDateTime);
		return list;
	}
	/**
	 * 修改文章状态如：加精、置顶
	 * 先查后改
	 * 判断之前状态
	 */
	@Override
	public Result editArt(Long artId, Integer top, Integer cream) {
		
		
		Article oldArticle = articleMapper.findArtById(artId);
		if(oldArticle!=null && top==1 && oldArticle.getTop()==1&& cream==1 && oldArticle.getCream()==1) {
			return Result.of(101, "已置顶、加精，无需修改");
		}
		oldArticle.setTop(top);
		oldArticle.setCream(cream);
		articleMapper.editArt(oldArticle);
		return Result.of(103, "操作成功");
	}	
	/**
	 * 管理员删除某文章
	 * 先查再删
	 * 先删除相关联标签表数据，再删文章
	 */
	@Override
	public Result adminDelArt(Long id) {
		User loginUser = LoginUtil.getLoginUser();
		Article art = articleMapper.findArtById(id);
		//给文章作者发送通知
		User artAuthor = userMapper.findUserById(art.getUser().getId());
		Notify notify = new Notify();
		notify.setAvatar(loginUser.getAvatar());
		notify.setCreated(new Date());
		notify.setTitle(loginUser.getNickname());
		notify.setStaus(0);
		notify.setUrl("/ta/"+loginUser.getId());
		notify.setUser(artAuthor);
		String s2 = String.format("您曾发布标题为：《%s》的文章因被举报违规，现已删除", art.getTitle());
		notify.setContent(s2);
		notifyMapper.InsertDelArt(notify);
		//再进行删除
		articleMapper.delArtTagById(art.getId());
		//保存操作到日志
		Admin_Journal admin_Journal = new Admin_Journal();
		admin_Journal.setCreated(new Date());
		admin_Journal.setSource_user(LoginUtil.getLoginUser());
		String s3 = String.format("管理员帐号：%s对文章标题为《%s》的文章进行了删除操作", LoginUtil.getLoginUser().getUsername(),art.getTitle());
		admin_Journal.setContent(s3);
		admin_Journal.setType(1);
		journalMapper.insertJournal(admin_Journal);
		
		//System.out.println(art);
		Integer result = articleMapper.delArtById(art.getId());
		if(result == null) {
			return Result.of(101, "删除失败");
		}
		return Result.of(100,"删除成功");
	}

}
