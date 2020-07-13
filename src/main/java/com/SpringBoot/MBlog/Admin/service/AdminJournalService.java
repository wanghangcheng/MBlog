package com.SpringBoot.MBlog.Admin.service;

import java.util.List;
import java.util.Map;

public interface AdminJournalService {
	
	List<Map<String, Object>> findJournals(int pageNumber,int pageSize,String sort,String order,String username,String type);
}
