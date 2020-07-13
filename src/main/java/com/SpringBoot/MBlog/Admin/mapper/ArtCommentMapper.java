package com.SpringBoot.MBlog.Admin.mapper;

public interface ArtCommentMapper {
	
	//删除文章下评论
	void delCommentsByArt(Long art_id);
	//删除用户评论
	void delCommentsByUser(Long u_id);
}
