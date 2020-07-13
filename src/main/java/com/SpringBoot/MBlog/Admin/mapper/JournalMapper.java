package com.SpringBoot.MBlog.Admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.SpringBoot.MBlog.entity.Admin_Journal;

public interface JournalMapper {
	
	/**
	 * 保存操作
	 * @param journal
	 */
	void insertJournal(Admin_Journal journal);
	/**
	 * 查询操作日志列表
	 * @param user_id
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> findJournals(@Param("user_id")Long user_id,@Param("type")String type);
}
