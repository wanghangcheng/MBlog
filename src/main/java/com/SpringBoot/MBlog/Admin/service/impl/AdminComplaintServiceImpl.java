package com.SpringBoot.MBlog.Admin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.MBlog.Admin.mapper.ArticleMapper;
import com.SpringBoot.MBlog.Admin.mapper.ComplaintMapper;
import com.SpringBoot.MBlog.Admin.mapper.JournalMapper;
import com.SpringBoot.MBlog.Admin.mapper.NotifyMapper;
import com.SpringBoot.MBlog.Admin.mapper.UserMapper;
import com.SpringBoot.MBlog.Admin.service.AdminComplaintService;
import com.SpringBoot.MBlog.entity.Admin_Journal;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.Complaint;
import com.SpringBoot.MBlog.entity.Notify;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.util.LoginUtil;
import com.SpringBoot.MBlog.vo.Result;
import com.github.pagehelper.PageHelper;

@Service
public class AdminComplaintServiceImpl implements AdminComplaintService {

	@Autowired ComplaintMapper complaintMapper;
	@Autowired ArticleMapper articleMapper;
	@Autowired UserMapper userMapper;
	@Autowired JournalMapper journalMapper;
	@Autowired NotifyMapper notifyMapper;
	
	/**
	 * 使用list<map>返回查询到的所有文章投诉信息
	 */
	@Override
	public List<Map<String, Object>> findComplaintArts(int pageNumber, int pageSize, String sort, String art_title,String handle) {
		
		Long id = null;
		if(art_title!=null&&art_title!=" ") {
			Article article = articleMapper.findArtByTitle(art_title);
			id=article.getId();
		}
		PageHelper.startPage(pageNumber, pageSize, sort);
		List<Map<String, Object>> list = complaintMapper.findComplaintArt(id,handle);

		return list;
	}
	/**
	 * 修改处理结果
	 */
	@Override
	public Result editHandle(Long Id, String  content,Integer type) {
		
		Article article = articleMapper.findArtById(Id);
		User user = userMapper.findUserById(Id);
		//给被投诉作者发通知
		Notify notify = new Notify();
		notify.setAvatar(LoginUtil.getLoginUser().getAvatar());
		notify.setCreated(new Date());
		notify.setTitle(LoginUtil.getLoginUser().getNickname());
		notify.setStaus(0);
		notify.setUrl("/ta/"+LoginUtil.getLoginUser().getId());
		//保存操作到日志
		Admin_Journal admin_Journal = new Admin_Journal();
		admin_Journal.setCreated(new Date());
		admin_Journal.setSource_user(LoginUtil.getLoginUser());
		admin_Journal.setType(2);
		//判断是处理文章还是用户
		if(article!=null && type == 1) {
			//给被投诉文章的作者发通知
			User artAuthor = userMapper.findUserById(article.getUser().getId());
			notify.setUser(artAuthor);
			String s2 = String.format("您曾发布标题为：《%s》的文章,%s", article.getTitle(),content);
			notify.setContent(s2);
			notifyMapper.InsertDelArt(notify);
			
			//更改投诉中的处理结果
			List<Complaint> complaints = complaintMapper.findComplaintListByArtId(Id);
			for (Complaint complaint : complaints) {
				if(complaint.getHandle()==true) {
					return Result.of(102, "已通知过，无需通知");
				}else {
					complaint.setHandle(true);
					complaintMapper.editHandle(complaint);
				}
			}
			String s3 = String.format("管理员帐号：%s对文章标题为：《%s》的投诉进行了处理", LoginUtil.getLoginUser().getUsername(),article.getTitle());
			admin_Journal.setContent(s3);
		}else if (user != null && type == 0) {
			notify.setUser(user);
			String s2 = content;
			notify.setContent(s2);
			notifyMapper.InsertDelArt(notify);
			
			//更改投诉中的处理结果
			List<Complaint> complaints = complaintMapper.findComplaintListByUserId(Id);
			for (Complaint complaint : complaints) {
				if(complaint.getHandle()==true) {
					return Result.of(102, "已通知过，无需通知");
				}else {
					complaint.setHandle(true);
					complaintMapper.editHandle(complaint);
				}
			}
			
			String s3 = String.format("管理员帐号：%s对昵称为%s的帐号的投诉进行了处理", LoginUtil.getLoginUser().getUsername(),user.getNickname());
			admin_Journal.setContent(s3);
		}
		journalMapper.insertJournal(admin_Journal);
		return Result.of(103, "处理成功");
	}
	/**
	 * 查询用户投诉列表
	 */
	@Override
	public List<Map<String, Object>> findComplaintUsers(int pageNumber, int pageSize, String sort, String user_nickname,
			String handle) {
		Long id = null;
		if(user_nickname!=null&&user_nickname!=" ") {
			User user = userMapper.findUserByNickname(user_nickname);
			id=user.getId();
		}
		PageHelper.startPage(pageNumber, pageSize, sort);
		List<Map<String, Object>> list = complaintMapper.findComplaintUser(id, handle);

		return list;
	}

}
