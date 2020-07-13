package com.SpringBoot.MBlog.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SpringBoot.MBlog.entity.Forum;

public interface ForumDao extends JpaRepository<Forum, Long>{
	//通过status查询
	@Query("from Forum f where f.status=?1")
	Page<Forum> findByStatus(Boolean status,Pageable pageable);
	Forum findById(Long id);
}
