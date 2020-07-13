package com.SpringBoot.MBlog.Admin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.MBlog.Admin.mapper.ArtCommentMapper;
import com.SpringBoot.MBlog.Admin.mapper.ArticleMapper;
import com.SpringBoot.MBlog.Admin.mapper.JournalMapper;
import com.SpringBoot.MBlog.Admin.mapper.NotifyMapper;
import com.SpringBoot.MBlog.Admin.mapper.UserFollowsMapper;
import com.SpringBoot.MBlog.Admin.mapper.UserMapper;
import com.SpringBoot.MBlog.Admin.mapper.UserPowerMapper;
import com.SpringBoot.MBlog.Admin.service.AdminUserService;
import com.SpringBoot.MBlog.entity.Admin_Journal;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.entity.User_Power;
import com.SpringBoot.MBlog.util.LoginUtil;
import com.SpringBoot.MBlog.vo.Result;
import com.github.pagehelper.PageHelper;

@Service
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired UserMapper userMapper;
	@Autowired UserPowerMapper userPowerMapper;
	@Autowired ArticleMapper articleMapper;
	@Autowired UserFollowsMapper userFollowsMapper;
	@Autowired ArtCommentMapper artCommentMapper;
	@Autowired NotifyMapper notifyMapper;
	@Autowired JournalMapper journalMapper;
	
	/**
	 * 通过使用list<map<string,object>>类型接收返回的所有用户数据，方便带粉丝数以及文章数
	 */
	@Override
	public List<Map<String, Object>> findUsers(int pageNumber, int pageSize, String sort, String nickname,
			String beginDateTime, String endDateTime, Integer state, Integer role) {
		
		PageHelper.startPage(pageNumber, pageSize, sort);
		List<Map<String, Object>> list = userMapper.findUserVos(nickname,beginDateTime,endDateTime,state,role);
		
		return list;
	}
	//修改用户
	//先查后改
	@Override
	public Result editUser(Long userId, String username, String email, Integer state, Integer role) {
		//System.out.println(userId+" "+username+" "+email+" "+state+" "+role);
		User oldUser = userMapper.findUserById(userId);
		//保存操作到日志
		Admin_Journal admin_Journal = new Admin_Journal();
		admin_Journal.setCreated(new Date());
		admin_Journal.setSource_user(LoginUtil.getLoginUser());
		String s2 = String.format("管理员帐号：%s对用户昵称为：%s的用户的信息进行了修改操作", LoginUtil.getLoginUser().getUsername(),oldUser.getNickname());
		admin_Journal.setContent(s2);
		admin_Journal.setType(2);
		journalMapper.insertJournal(admin_Journal);
		//System.out.println(oldUser);
		Integer result = userMapper.editUser(oldUser.getId(), username, email, state, role);
		if(result==0) {
			return Result.of(100, "修改失败");
		}
		return Result.of(101, "修改成功");
	}
	/**先查后删
	 * 先删除权限表记录（power表）
	 * 删除用户发表的文章（article表，comment表，article_tag表，favor表
	 * 	先评论，标签记录，文章喜欢
	 * ）
	 * 删除关注记录（follows表）
	 * 删除粉丝记录（follow表）是否通知？
	 * 删除其他用户动态（notify表）
	 * 最后删除用户数据（user表）
	 */
	@Override
	public Result delUser(Long id) {

		User loginUser = LoginUtil.getLoginUser();
		User oldUser = userMapper.findUserById(id);
		User_Power oldPower = userPowerMapper.findPowerByUserId(oldUser.getId());
		if(id==loginUser.getId()&&oldPower.getRole()==2) {
			//System.out.println(id+" "+loginUser.getId());
			return Result.of(100,"不能删除该账号");
		}
		//找出该用户下的所有文章，然后分别对每篇文章下的评论，标签，喜欢做删除,最后删除该用户所有文章
		List<Article> artList = articleMapper.findArtsByUserId(oldUser.getId());
		if(artList!=null&&artList.size()>0) {
			for(Article article :artList) {
				artCommentMapper.delCommentsByArt(article.getId());
				articleMapper.delArtTagById(article.getId());
				articleMapper.delFavorByArtId(article.getId());
			}
		}
		articleMapper.delFavorByUserId(oldUser.getId());
		articleMapper.delArtByUserId(oldUser.getId());
		//删除该用户在别人文章中的评论
		artCommentMapper.delCommentsByUser(oldUser.getId());
		//删除该用户的关注记录以及粉丝记录
		userFollowsMapper.delFollwsByUserId(oldUser.getId());
		userFollowsMapper.delFansByUserId(oldUser.getId());
		//删除notify表中记录
		notifyMapper.DelNotifyByUserId(oldUser.getId());
		//保存操作到日志
		Admin_Journal admin_Journal = new Admin_Journal();
		admin_Journal.setCreated(new Date());
		admin_Journal.setSource_user(LoginUtil.getLoginUser());
		String s2 = String.format("管理员帐号：%s对用户昵称为：%s的用户的信息进行了删除操作，并删除了TA的相关记录", LoginUtil.getLoginUser().getUsername(),oldUser.getNickname());
		admin_Journal.setContent(s2);
		admin_Journal.setType(1);
		journalMapper.insertJournal(admin_Journal);
		//删除用户记录
		userMapper.delNotifyByUser(oldUser.getId());
		userMapper.delUser(oldUser.getId());
		userPowerMapper.delPowerById(oldPower.getId());
		
		return Result.of(102, "删除成功");
	}	
}
