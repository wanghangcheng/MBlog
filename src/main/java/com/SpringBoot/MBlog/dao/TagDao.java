package com.SpringBoot.MBlog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.MBlog.entity.Tag;

public interface TagDao extends JpaRepository<Tag, Long>{
	/**
	 * 通过name查询tag标签
	 * @param name
	 * @return
	 */
	Tag findByName(String name);
}
