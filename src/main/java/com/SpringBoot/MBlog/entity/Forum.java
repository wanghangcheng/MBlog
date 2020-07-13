package com.SpringBoot.MBlog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 版块，栏目
 * @author hangcheng
 *
 */
@Entity
@Table(name="blog_forum")
public class Forum {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;//板块名称
	private String code;//板块标识,使用字母和数字，访问的时候会作为URL的一部分
	private Boolean status;//状态（true:显示，数据库表示1，flase：隐藏，数据库表示为0）
	private Integer weight;//权重  （值越小的排的越前）
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Forum [id=" + id + ", name=" + name + ", code=" + code + ", status=" + status + "]";
	}
	
}
