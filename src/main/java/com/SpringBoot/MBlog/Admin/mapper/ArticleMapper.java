package com.SpringBoot.MBlog.Admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.SpringBoot.MBlog.entity.Article;

public interface ArticleMapper {
	
	//排行榜时间和热度查询接口
	List<Article> ApiFindArtByTime(int end);
	List<Article> ApiFindArtByHit(int end);
	
	//后台系统接口
	List<Article> findArticles(@Param("nickname")String nickname,@Param("artTitle")String artTitle,@Param("beginDateTime")String beginDateTime,@Param("endDateTime")String endDateTime);
	
	//根据id查询文章
	Article findArtById(Long id);
	
	//根据id删除文章，先删除与标签相关联表中数据再删除文章
	void delArtTagById(Long art_id);
	Integer delArtById(Long art_id);
	
	//删除用户前删除用户之前发布的文章
	void delArtByUserId(Long u_id);
	
	//删除用户前删除用户喜欢的文章记录
	void delFavorByArtId(Long art_id);
	void delFavorByUserId(Long u_id);
	
	//查询某用户所有文章
	List<Article> findArtsByUserId(Long id);
	//查询某版块下所有的文章
	List<Article> findArticlesByForumId(Long id);
	//将某版块下的所有文章转至博文板块下
	Integer EditArticlesByForumId(Long id);
	//标题查询相似文章
	Article findArtByTitle(String art_title);
	//修改文章（加精、置顶）
	void editArt(Article article);
	//首页文章查询
	List<Article> findHomeArtByCream (int end);
	List<Article> findHomeArtByHits (int end);
}
