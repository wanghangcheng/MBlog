package com.SpringBoot.MBlog.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.SpringBoot.MBlog.entity.Notify;

public interface NotifyDao extends JpaRepository<Notify, Long> {
	
	@Query("from Notify n where n.user.id=?1")
	Page<Notify> findByUser(long userId,Pageable pageable);

	@Query("from Notify n where n.user.id=?1 and n.staus=?2")
	Page<Notify> findByUserStaus(long userId,Integer staus,Pageable pageable);
	
	//用户修改昵称时，更新表中title
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update blog_notify n set n.title =?1 where n.title = ?2",nativeQuery = true)
	void UpdateTiTleOnUser(String new_nickname,String old_nickname);
	//用户修改头像时，更新表中title
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update blog_notify n set n.avatar =?1 where n.avatar = ?2",nativeQuery = true)
	void UpdateAvatarOnUser(String new_avatar,String old_avatar);
	
}
