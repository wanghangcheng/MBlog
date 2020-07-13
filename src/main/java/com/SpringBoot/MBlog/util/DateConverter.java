package com.SpringBoot.MBlog.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {
	
	private String[] patterns; //yyyy-MM-dd       yyyy/MM/dd    yyyy.MM.dd
	private List<SimpleDateFormat> list ;

	public void setPatterns(String[] patterns) {
		this.patterns = patterns;
	}

	private void init() {
		list = new ArrayList<SimpleDateFormat>();
		for(String pattern:patterns) {
			list.add(new SimpleDateFormat(pattern));
		}
	}

	@Override
	public Date convert(String source) {
		if(source==null||source.trim().length()==0)return null;
		if(list==null)init();
		for(SimpleDateFormat sdf:list) {
			try {
				return sdf.parse(source);
			} catch (Exception e) {
				continue;
			}
		}
		throw new RuntimeException("DateConverterError:"+source);
	}
	

	
}
