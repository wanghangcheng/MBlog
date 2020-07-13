package com.SpringBoot.MBlog.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 文章
 * @author hangcheng
 * @version 1.0
 */

@Entity
@Table(name="blog_article")
public class Article {
	
	private long id;
	private User user;//发文人
	private String title;//文章标题
	private Date createTime;//发文时间
	private long hits;//点击数
	private String content;//文章内容
	private Forum forum;//所属板块
	private Integer top;//置顶，0：不置顶，1：置顶
	private Integer cream;//加精，0：不加精，1：加精
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@ManyToOne//用户和文章的关系要说明
	@JoinColumn(name="user_id")
	@Cascade(CascadeType.SAVE_UPDATE)//级联保存、级联更新,不级联删除（删除文章的时候不会删除用户）
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@JsonFormat(pattern="yyyy-MM-dd hh:mm",timezone="GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public long getHits() {
		return hits;
	}
	public void setHits(long hits) {
		this.hits = hits;
	}
	@Column(columnDefinition="TEXT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@ManyToOne//板块和文章的关系要说明
	@JoinColumn(name="group_id")
	@Cascade(CascadeType.SAVE_UPDATE)//级联保存、级联更新,不级联删除（删除文章的时候不会删除板块）
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public Integer getTop() {
		return top;
	}
	public void setTop(Integer top) {
		this.top = top;
	}
	public Integer getCream() {
		return cream;
	}
	public void setCream(Integer cream) {
		this.cream = cream;
	}
	
	
	@Transient
	private long like;//文章喜欢数
	@Transient
	private long comment;//文章评论数
	@Transient
	private Integer type;//首页文章类型,0:精品文章，1：热门推荐
	@Transient
	private List<String> tags;//文章标签
	@Transient
	public long getLike() {
		return like;
	}
	public void setLike(long like) {
		this.like = like;
	}
	@Transient
	public long getComment() {
		return comment;
	}
	public void setComment(long comment) {
		this.comment = comment;
	}
	@Transient
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", user=" + user + ", title=" + title + ", createTime=" + createTime + ", hits="
				+ hits + ", content=" + content + ", forum=" + forum + ", top=" + top + ", cream=" + cream + ", like="
				+ like + ", comment=" + comment + ", type=" + type + ", tags=" + tags + "]";
	}
	
	
}
