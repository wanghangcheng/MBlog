package com.SpringBoot.MBlog.Admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.MBlog.Admin.mapper.JournalMapper;
import com.SpringBoot.MBlog.Admin.mapper.UserMapper;
import com.SpringBoot.MBlog.Admin.service.AdminJournalService;
import com.SpringBoot.MBlog.entity.User;
import com.github.pagehelper.PageHelper;

@Service
public class AdminJournalServiceImpl implements AdminJournalService {
	
	@Autowired UserMapper userMapper;
	@Autowired JournalMapper journalMapper;
	
	/**
	 * 查询操作日志列表
	 */
	@Override
	public List<Map<String, Object>> findJournals(int pageNumber, int pageSize, String sort, String order,
			String username, String type) {
		Long id = null;
		if(username!=null&&username!=" ") {
			User user = userMapper.findUserByUsername(username);
			id=user.getId();
		}
		PageHelper.startPage(pageNumber, pageSize, sort);
		List<Map<String, Object>> list = journalMapper.findJournals(id, type);
		
		return list;
	}

}
