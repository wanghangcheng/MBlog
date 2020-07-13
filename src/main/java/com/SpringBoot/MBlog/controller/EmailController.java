package com.SpringBoot.MBlog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.MBlog.service.EmailService;

@RestController
public class EmailController {
	
	@Autowired EmailService emailService;
	/**
	 * 邮箱验证发送邮件相关
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/email",method=RequestMethod.POST)
    public Object sendEmail(@RequestParam String email )  {
        	return emailService.sendEmailVerCode(email);
    }
}
