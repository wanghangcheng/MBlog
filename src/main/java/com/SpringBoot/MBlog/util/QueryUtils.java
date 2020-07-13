package com.SpringBoot.MBlog.util;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 后台分页查询条件的封装类
 * @author 95298
 *
 */
public class QueryUtils extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	// 偏移页数
	private int offset;
	// 每页条数
	private int limit;

	public QueryUtils(Map<String, Object> params) {
		this.putAll(params);
		// 分页参数
		this.offset = Integer.parseInt(params.get("offset").toString());
		this.limit = Integer.parseInt(params.get("limit").toString());
		this.put("offset", offset);
		this.put("page", offset / limit + 1);
		this.put("limit", limit);
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.put("offset", offset);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}