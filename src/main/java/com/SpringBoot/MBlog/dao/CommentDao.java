package com.SpringBoot.MBlog.dao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SpringBoot.MBlog.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {
	/**
	 *通过文章id查询评论分页 
	 * @param articleId
	 * @param pageable
	 * @return
	 */
	@Query("from Comment c where c.article.id=?1")
	Page<Comment> findByArticleId(Long articleId,Pageable pageable);
	
	/**
	 *通过文章id查询评论数量 
	 * @param articleId
	 * @param pageable
	 * @return
	 */
	@Cacheable(value="CommentCount",key="#p0")
	@Query("select count(*) from Comment c where c.article.id=?1")
	int countByArticleId(Long articleId);
	
	/**
	 * 通过用户id查询发表的评论数
	 * @param userId
	 * @return
	 */
	@Query("select count(id) from Comment c where c.author.id=?1")
	int countByUser(Long userId);
	
	/**
	 * 通过用户id查询发表的评论
	 * @param userId
	 * @return
	 */
	@Query("from Comment c where c.author.id=?1")
	Page<Comment> findByUserId(Long userId,Pageable pageable);
	
	@Query("from Comment c where c.author.id=?1 and c.id=?2")
	Comment find(long userId,long commentId);
	
	/**
	 * 查询某评论的回复数
	 * 返回 ：0就是无回复，否则有回复
	 */
	@Query("select count(id) from Comment c where c.target.id=?1")
	int countComment(long commentId);
	
}
