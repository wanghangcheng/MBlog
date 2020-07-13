package com.SpringBoot.MBlog.vo;


public class HitUtilVo {
	private String userIP;   //访问的用户ip
	private long ArticleId;	 //访问的文章id
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	public long getArticleId() {
		return ArticleId;
	}
	public void setArticleId(long articleId) {
		ArticleId = articleId;
	}

}
