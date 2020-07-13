package com.SpringBoot.MBlog.test;

import com.SpringBoot.MBlog.util.EmailUtil;

public class TestEmailUtil {
	public static void main(String[] args) {
		String  string ="1459623930@qq.com";
		boolean flag = EmailUtil.isEmail(string);
		System.out.println(flag);
	}
}
