package com.SpringBoot.MBlog.vo;

import com.SpringBoot.MBlog.entity.User;

public class UserVo extends User{
	
	private int articleCount;  //该用户发表的文章数
	private int commtentCount;  //该用户发表的评论数
	private int fansCount;      //该用户的粉丝数
	
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public int getCommtentCount() {
		return commtentCount;
	}
	public void setCommtentCount(int commtentCount) {
		this.commtentCount = commtentCount;
	}
	public int getFansCount() {
		return fansCount;
	}
	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}
	@Override
	public String toString() {
		return "UserVo [articleCount=" + articleCount + ", commtentCount=" + commtentCount + "]";
	}
	
	
}
