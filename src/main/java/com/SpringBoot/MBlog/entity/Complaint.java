package com.SpringBoot.MBlog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "blog_complaint")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Complaint {
	
	private Long id;
	private Integer type;			//投诉类型（0：用户，1：文章）
	private Integer genre;			//投诉类别（0：政治，1：色情，2：广告，3：其他）
	private String remarks;			//补充说明
	private Article target_art;		//被投诉文章
	private User target_user;		//被投诉作者
	private Boolean handle;			//是否处理
	private Date created;			//投诉时间
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getGenre() {
		return genre;
	}
	public void setGenre(Integer genre) {
		this.genre = genre;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@ManyToOne
	@JoinColumn(name="article_id")
	public Article getTarget_art() {
		return target_art;
	}
	public void setTarget_art(Article target_art) {
		this.target_art = target_art;
	}
	@ManyToOne
	@JoinColumn(name="user_id")
	public User getTarget_user() {
		return target_user;
	}
	public void setTarget_user(User target_user) {
		this.target_user = target_user;
	}
	@JsonFormat(pattern="yyyy-MM-dd hh:mm",timezone="GMT+8")
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Boolean getHandle() {
		return handle;
	}
	public void setHandle(Boolean handle) {
		this.handle = handle;
	}
	@Override
	public String toString() {
		return "Complaint [id=" + id + ", type=" + type + ", genre=" + genre + ", remarks=" + remarks + ", target_art="
				+ target_art + ", target_user=" + target_user + ", handle=" + handle + ", created=" + created + "]";
	}
	
	
}
