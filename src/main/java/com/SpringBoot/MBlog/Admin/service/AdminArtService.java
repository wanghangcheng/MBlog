package com.SpringBoot.MBlog.Admin.service;

import java.util.List;

import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.vo.Result;

public interface AdminArtService {
	
	//获取文章列表
	List<Article> findArticles(int pageNumber,int pageSize,String sort,String nickname,String artTitle,String beginDateTime,String endDateTime);
	//修改文章状态
	Result editArt(Long artId,Integer top,Integer cream);
	//删除文章
	Result adminDelArt(Long id);
}
