package com.SpringBoot.MBlog.Admin.mapper;


import com.SpringBoot.MBlog.entity.Notify;

public interface NotifyMapper {
	
	//删除文章时给作者发通知
	void InsertDelArt(Notify notify);
	//删除用户时删除该用户发过的通知以及状态
	void DelNotifyByUserId(Long u_id);
}
