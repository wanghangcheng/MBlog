package com.SpringBoot.MBlog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.MBlog.entity.Complaint;

public interface ComplaintDao extends JpaRepository<Complaint, Long> {
	

}
