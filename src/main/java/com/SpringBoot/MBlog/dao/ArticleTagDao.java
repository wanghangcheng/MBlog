package com.SpringBoot.MBlog.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.ArticleTag;
import com.SpringBoot.MBlog.entity.Tag;

public interface ArticleTagDao extends JpaRepository<ArticleTag, Long>{
	
	@Cacheable(value="articleTags",key="#p0")
	@Query("select t.tag from ArticleTag t where t.article.id=?1")
	List<Tag> findTagsByArticle(long articleId);
	
	@Query("from ArticleTag t where t.article.id=?1")
	List<ArticleTag> findByArticleId(long articleId);
	
	@Query("from ArticleTag t where t.tag.id=?1")
	List<ArticleTag> findByTagId(long TagId);
	
	@Query("select t.article from ArticleTag t where t.tag.id=?1")
	Page<Article> articlesFindTagId(long TagId,Pageable pageable);
}
