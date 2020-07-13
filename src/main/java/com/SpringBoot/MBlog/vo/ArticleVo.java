package com.SpringBoot.MBlog.vo;

import com.SpringBoot.MBlog.entity.Article;

public class ArticleVo extends Article {
	private long like; //喜欢数
	private long comment;//评论数
	public long getLike() {
		return like;
	}
	public void setLike(long like) {
		this.like = like;
	}
	public long getComment() {
		return comment;
	}
	public void setComment(long comment) {
		this.comment = comment;
	}
	
	
}
