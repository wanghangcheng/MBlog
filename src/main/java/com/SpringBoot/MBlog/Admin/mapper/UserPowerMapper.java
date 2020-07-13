package com.SpringBoot.MBlog.Admin.mapper;

import com.SpringBoot.MBlog.entity.User_Power;

public interface UserPowerMapper {

	//删除用户前删除用户权限
	void delPowerById(Long u_id);
	User_Power findPowerByUserId(Long id);
}
