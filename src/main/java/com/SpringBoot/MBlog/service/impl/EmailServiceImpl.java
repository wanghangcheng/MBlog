package com.SpringBoot.MBlog.service.impl;


import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import com.SpringBoot.MBlog.service.EmailService;
import com.SpringBoot.MBlog.util.SendEmail;
import com.SpringBoot.MBlog.util.VerCodeGenerateUtil;
import com.SpringBoot.MBlog.vo.Result;

@Service
public class EmailServiceImpl implements EmailService {
	
	/**
     * 邮件发送
     * @param receiver 收件人
     * @param verCode 验证码
	 * @return 
     * @throws MailSendException 邮件发送错误
     */
	
	public Result sendEmailVerCode(String receiver) {
		String code = VerCodeGenerateUtil.generateVerCode();
		System.out.println(code);
		boolean flag = SendEmail.SendCode(receiver, code);
		if(flag) {
			return Result.of(100, "发送成功",code);
		}
        return  Result.of(200, "发送失败");
	}
}
