package com.SpringBoot.MBlog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 关注
 * @author hangcheng
 * @version 1.0
 */

@Entity
@Table(name="blog_follows")
public class Follows {
	
	private Long id;
	private User source;  //发起关注的人
	private User target;  //被关注的人
	private Date created;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="source_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	public User getSource() {
		return source;
	}
	public void setSource(User source) {
		this.source = source;
	}
	@ManyToOne
	@JoinColumn(name="target_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	public User getTarget() {
		return target;
	}
	public void setTarget(User target) {
		this.target = target;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Override
	public String toString() {
		return "Follow [id=" + id + ", source=" + source + ", target=" + target + ", created=" + created + "]";
	}
	
	
	
	
}
