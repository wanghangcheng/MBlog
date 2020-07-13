package com.SpringBoot.MBlog.vo;

import java.util.HashMap;
import java.util.List;

public class EasyuiResult extends HashMap<String, Object>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static EasyuiResult build(long total,List<?> rows) {
		EasyuiResult er = new EasyuiResult();
		er.put("total", total);
		er.put("rows", rows);
		return er;
	}
}
