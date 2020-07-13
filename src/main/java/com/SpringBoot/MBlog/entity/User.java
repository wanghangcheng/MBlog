package com.SpringBoot.MBlog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户
 * @author hangcheng
 * @version 1.0
 */

@Entity
@Table(name="blog_user")
public class User {
	
	private long id;
	private String username;//用户名
	private String password;//密文密码
	private String salt;//密码盐值
	private String email;//邮箱
	private String phone;//手机
	private String sex;//性别
	private String nickname;//昵称
	private String sign;//个性签名
	private String avatar;//头像
	private Date createTime;//创建时间
	private int state;//状态（0:未激活，1：正常，2：禁用） 用于邮箱激活
	private User_Power power;//权限表
	private Date last_login_time;//最后登录时间
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@JsonFormat(pattern="yyyy-MM-dd hh:mm",timezone="GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@OneToOne
	@JoinColumn(name = "power_id")
	@Cascade(CascadeType.REMOVE) 
	public User_Power getPower() { 
		return power; 
	}
	public void setPower(User_Power power) {
		this.power = power; 
	}
	@JsonFormat(pattern="yyyy-MM-dd hh:mm",timezone="GMT+8")
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", email="
				+ email + ", phone=" + phone + ", sex=" + sex + ", nickname=" + nickname + ", sign=" + sign
				+ ", avatar=" + avatar + ", createTime=" + createTime + ", state=" + state + ", power=" + power + "]";
	}
	
	
	
}
