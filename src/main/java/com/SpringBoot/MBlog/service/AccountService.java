package com.SpringBoot.MBlog.service;

import javax.servlet.http.HttpServletRequest;

import com.SpringBoot.MBlog.vo.Result;
/**
 * 头像上传
 * @author hangcheng
 *
 */
public interface AccountService {
	
	Result updatePassword (String oldPassword,String newPassword);
	
	Result updateProfile(String nickname, String sign);
	
	Result updateAvatar(int x,int y,int width,int height,String path ,HttpServletRequest request);
	
	Result saveFavor(long articleId);
	
	Result saveFollow(long id);
	/** 检查当前登录用户是否关注了某个人 */
	Result followCheck(Long id);
	/** 取消关注某个人 */
	Result unFollow(long id);
	/** 取消喜欢哪篇文章 */
	Result unfavor(long id);
	/** 修改邮箱*/
	Result editEmail(String newEmail);
	/** 忘记密码并修改成新密码*/
	Result editPwd(String email,String password);
}
