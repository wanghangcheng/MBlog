package com.SpringBoot.MBlog.Admin.service;

import java.util.List;
import java.util.Map;

import com.SpringBoot.MBlog.vo.Result;

public interface AdminComplaintService {
	
	//查询所有文章投诉信息
	List<Map<String, Object>> findComplaintArts(int pageNumber,int pageSize,String sort,String art_title,String handle);
	//查询所有文章投诉信息
	List<Map<String, Object>> findComplaintUsers(int pageNumber,int pageSize,String sort,String user_nickname,String handle);
	//修改处理信息
	Result editHandle(Long Id,String content,Integer type);
}
