package com.SpringBoot.MBlog.dao;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SpringBoot.MBlog.entity.User;

public interface UserDao extends JpaRepository<User, Long>{
	//通过用户名查询用户
	User findByUsername(String username);
	//通过用户邮箱查询用户
	User findByEmail(String email);
	//通过用户昵称查询用户
	User findByNickname(String nickname);
	//通过id查询用户
	User findById(long id);
	/**
	 * 通过关键词查询用户
	 * @param userId
	 * @return
	 */
	@Query("from User u where u.nickname like CONCAT('%',:key,'%')")
	Page<User> search(@Param("key")String key,Pageable pageable);
	
	/**
	 * 更新最后登录时间
	 * @param lastLoginTime
	 */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update blog_user u set u.last_login_time =?1 where u.id = ?2",nativeQuery = true)
	void updateLoginDate(Date lastLoginTime,Long u_id);
}
