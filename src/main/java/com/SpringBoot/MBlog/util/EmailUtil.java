package com.SpringBoot.MBlog.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtil {
	/**
	 * 
	 * @param string
	 * @return true为邮箱，false为不是邮箱
	 */
	public static boolean isEmail(String string) {
		 if (null==string || "".equals(string)) return false;  
         Pattern p =  Pattern.compile("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");//复杂匹配
         Matcher m = p.matcher(string);
         return m.matches();
	}
}
