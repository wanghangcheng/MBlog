package com.SpringBoot.MBlog.Admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.SpringBoot.MBlog.entity.Forum;

public interface ForumMapper {
	
	/**
	 * 查询forum对象
	 * @param id
	 * @return
	 */
	Forum findForumById(Long id);
	/**
	 * 通过权重查询某对象
	 * @param weight
	 * @return
	 */
	Forum findForumByWeight(Integer weight);
	/**
	 * 根据name查询板块
	 * @param name
	 * @return
	 */
	Forum findForumByName(String name);
	/**
	 * 根据code查询板块
	 * @param code
	 * @return
	 */
	Forum findForumByCode(String code);
	/**
	 * 获取某状态下的板块数
	 * @return
	 */
	Integer findStatusCount(Integer status);
	/**
	 * 查询所有板块信息（可根据status查询）
	 * @param status
	 * @return
	 */
	List<Map<String, Object>> findForumVos(@Param("name")String name,@Param("status")String status);
	/**
	 * 修改forum信息
	 * @param new_forum
	 * @return
	 */
	Integer editForum (Forum new_forum);
	/**
	 * 获取forum列表
	 * @return
	 */
	List<Forum> findListByStatus(Integer Status);
	/**
	 * 删除某版块
	 * @param id
	 * @return
	 */
	Integer delForumById(Long id);
	/**
	 * 新增板块
	 * @param forum
	 */
	void insertForum(Forum forum);
	
}
