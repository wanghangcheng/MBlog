package com.SpringBoot.MBlog.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SpringBoot.MBlog.entity.Follows;

public interface FollowsDao extends JpaRepository<Follows, Long> {
	
	//我关注了谁
	@Query("from Follows f where f.source.id=?1")
	Page<Follows> findFollows(long sourceId,Pageable pageable);
	//谁关注了我，粉丝
	@Query("from Follows f where f.target.id=?1")
	Page<Follows> findFans(long userId,Pageable pageable);
	//一个关注了另一个人记录
	@Query("from Follows f where f.source.id=?1 and f.target.id=?2")
	Follows find(Long source,Long target);
	//查询有多少个粉丝
	@Query("select count(id) from Follows  f where f.target.id=?1")
	Integer countFans(long targetId);
}
