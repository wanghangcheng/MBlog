package com.SpringBoot.MBlog.Admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.SpringBoot.MBlog.entity.Complaint;

public interface ComplaintMapper {
	/**
	 * 查询文章投诉列表
	 * @param art_id
	 * @param handle
	 * @return
	 */
	List<Map<String, Object>> findComplaintArt(@Param("art_id") Long art_id,@Param("handle")String handle);
	/**
	 *  查询用户投诉列表
	 * @param user_id
	 * @param handle
	 * @return
	 */
	List<Map<String, Object>> findComplaintUser(@Param("user_id") Long user_id,@Param("handle")String handle);
	/**
	 * 通过id查询投诉信息
	 * @param id
	 * @return
	 */
	Complaint findComplaintById(Long id);
	/**
	 * 修改投诉处理状态
	 * @param complaint
	 * @return
	 */
	Integer editHandle(Complaint complaint);
	/**
	 * 根据文章id查询投诉列表
	 * @param art_id
	 * @return
	 */
	List<Complaint> findComplaintListByArtId(Long art_id);
	/**
	 * 根据用户id查询投诉列表
	 * @param user_id
	 * @return
	 */
	List<Complaint> findComplaintListByUserId(Long user_id);
}
