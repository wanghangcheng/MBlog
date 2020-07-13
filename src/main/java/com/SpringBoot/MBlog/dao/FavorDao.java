package com.SpringBoot.MBlog.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SpringBoot.MBlog.entity.Favor;

public interface FavorDao extends JpaRepository<Favor, Long>{

	@Query("from Favor f where f.user.id=?1 and f.article.id=?2")
	Favor find(long userId,long articleId);
	/**
	 * 通过文章id查询喜欢数
	 * @param articleId
	 * @return
	 */
	@Cacheable(value="favorCount",key="#p0")
	@Query("select count(id) from Favor  f where f.article.id=?1")
	int countByArticle(long articleId);
	
	@Query("from Favor  f where f.user.id=?1")
	Page<Favor> favorByUserId(long userId,Pageable pageable);
	
	@Query("from Favor  f where f.article.id=?1")
	List<Favor> favorByArticleId(long articleId);
}

