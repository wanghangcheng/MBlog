package com.SpringBoot.MBlog.service;

import com.SpringBoot.MBlog.vo.Result;

public interface CommentService {
	
	Result save(Long articleId,Long targetCommentId,String text);

	Result list(Long articleId, int pageSize, int pn);

	Result delete(long id);
	
}
