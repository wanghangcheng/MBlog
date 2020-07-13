package com.SpringBoot.MBlog.service;


import javax.mail.MessagingException;

import com.SpringBoot.MBlog.vo.Result;


public interface EmailService {
	/**
     * 邮件发送
     * @param receiver 收件人
     * @param verCode 验证码
	 * @return 
	 * @throws MessagingException 
     * 
     */
	//注册
	Result sendEmailVerCode(String receiver);
	//修改邮箱
	//Result editSendEmail(String receiver);
}
