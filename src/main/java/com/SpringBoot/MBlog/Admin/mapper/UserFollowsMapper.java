package com.SpringBoot.MBlog.Admin.mapper;

public interface UserFollowsMapper {
	
	//删除用户前删除他的关注以及他的粉丝
	void delFollwsByUserId(Long u_id);
	void delFansByUserId(Long u_id);
}
