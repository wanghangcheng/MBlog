package com.SpringBoot.MBlog.service;

import java.util.Map;

import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.vo.Result;
/**
 * 登录验证
 * @author hangcheng
 *
 */
public interface RegisterService {

	Map<String, Object> register(User user);
	Result login(String username,String password);
}
