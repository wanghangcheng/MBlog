package com.SpringBoot.MBlog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringContext {
	
	private static ApplicationContext context;
	
	@Autowired
	public void set(ApplicationContext applicationContext) {
		context=applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return context;
	}
}
