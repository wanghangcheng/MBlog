package com.SpringBoot.MBlog.util;
import java.io.Serializable;
import java.util.List;
 /**
  * 后台分页查询结果的封装类
  * @author 95298
  *
  */
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private int total;
	private List<?> rows;
 
	public PageUtils(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}
 
	public int getTotal() {
		return total;
	}
 
	public void setTotal(int total) {
		this.total = total;
	}
 
	public List<?> getRows() {
		return rows;
	}
 
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
 
}
