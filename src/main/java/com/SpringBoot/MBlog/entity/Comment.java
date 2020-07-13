package com.SpringBoot.MBlog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 文章评论
 * @author hangcheng
 *
 */
@Entity
@Table(name="blog_comment")
public class Comment {
	
	private Long id;
	private Article article;  //文章
	private Date created;    //评论时间
	private User author;     //评论人
	private String content;  //评论内容
	private Comment target;  //回复的目标
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="article_id")
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@ManyToOne
	@JoinColumn(name="author_id")
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	@Column(columnDefinition="TEXT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@ManyToOne
	@JoinColumn(name="target_id")
	public Comment getTarget() {
		return target;
	}
	public void setTarget(Comment target) {
		this.target = target;
	}
	
	
	
}
