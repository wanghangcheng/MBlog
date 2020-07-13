package com.SpringBoot.MBlog.dao;


import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SpringBoot.MBlog.entity.Article;

public interface ArticleDao extends JpaRepository<Article, Long>{
	
	/**
	 * 通过文章id查询文章
	 * @param userId
	 * @return
	 */
	@Cacheable(value="findArticle",key="#p0")
	@Query("from Article a where a.id=?1")
	Article findById(long articleId);

	
	/**
	 * 通过板块查文章
	 * @param forumCode
	 * @param pageable
	 * @return
	 */
	@Query("from Article a where a.forum.code=?1")
	Page<Article> findByForumCode(String forumCode,Pageable pageable);
	
	/**
	 * 通过id查所写文章
	 * @param forumCode
	 * @param pageable
	 * @return
	 */
	@Query("from Article a where a.user.id=?1")
	Page<Article> findByUserId(long userId,Pageable pageable);
	
	/**
	 * 通过用户id查询发表的文章数
	 * @param userId
	 * @return
	 */
	@Query("select count(id) from Article a where a.user.id=?1")
	int countByUser(long userId);
	
	/**
	 * 通过用户id和文章id查询文章
	 * @param userId
	 * @return
	 */
	@Query("from Article a where a.user.id=?1 and a.id=?2")
	Article find(long userId,long articleId);

	/**
	 * 通过关键词查询文章
	 * @param userId
	 * @return
	 */
	@Query("from Article a where a.title like CONCAT('%',:key,'%') or "
			+"a.content like CONCAT('%',:key,'%')")
	Page<Article> search(@Param("key")String key,Pageable pageable);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update blog_article a set a.hits =?1 where a.id = ?2",nativeQuery = true)
	void updateHit(long hits,long articleId);
}
