package com.SpringBoot.MBlog.Admin.service;

import java.util.List;
import java.util.Map;

import com.SpringBoot.MBlog.vo.Result;


public interface AdminForumService {
	
	//查询所有板块信息
	List<Map<String, Object>> findForums(int pageNumber,int pageSize,String sort,String name,String status);
	//修改版块信息
	Result editFotum(Long f_id,String f_name,Integer f_status,Integer weight);
	//删除某版块
	Result delForumById(Long id);
	//新增版块
	Result insertForum(String name,String code,Integer weight);
}
