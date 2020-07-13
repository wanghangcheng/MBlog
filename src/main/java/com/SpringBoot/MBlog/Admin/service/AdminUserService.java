package com.SpringBoot.MBlog.Admin.service;

import java.util.List;
import java.util.Map;

import com.SpringBoot.MBlog.vo.Result;

public interface AdminUserService {
	
	//查询所有用户
	List<Map<String, Object>> findUsers(int pageNumber,int pageSize,String sort,String nickname,String beginDateTime,String endDateTime,Integer state,Integer role);
	//修改用户
	Result editUser(Long userId,String username,String email,Integer state,Integer role);
	//删除用户
	Result delUser(Long id);
}
