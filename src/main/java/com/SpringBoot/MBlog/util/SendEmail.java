package com.SpringBoot.MBlog.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {
//	
	@Autowired  //自动注入发件时间过慢
	JavaMailSenderImpl mailSender;
	public static SendEmail sendEmail;
	
	@PostConstruct
    public void init() {
		sendEmail = this;
    }
	@Value("${spring.mail.username}")
	private  String sender ;

	public static Boolean SendCode(String Email,String code) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("博客系统验证码");	//设置邮件标题
        message.setText("尊敬的用户,您好:\n"
                + "\n本次请求的邮件验证码为:" + code + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");	//设置邮件正文
        message.setTo(Email);	//设置收件人
        message.setFrom(sendEmail.sender);	//设置发件人
        sendEmail.mailSender.send(message);	//发送邮件
        return true;
	}
	
	public static Boolean SendPassword(String Email,String Password) {
		
		SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("用户密码修改");	//设置邮件标题
        message.setText("尊敬的用户,您好:\n"
                + "\n本次帐号密码已修改为："+Password+"。\n"
                + "\n如非本人操作，请查看账户。\n(这是一封自动发送的邮件，请不要直接回复）");	//设置邮件正文
        message.setTo(Email);	//设置收件人
        message.setFrom(sendEmail.sender);	//设置发件人
        sendEmail.mailSender.send(message);	//发送邮件
        System.out.println("发送成功");
        return true;
	}
}
