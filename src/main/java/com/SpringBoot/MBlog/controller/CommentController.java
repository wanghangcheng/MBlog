package com.SpringBoot.MBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SpringBoot.MBlog.service.CommentService;

@Controller
public class CommentController {
	
	@Autowired CommentService commentService;
	/**
	 * 评论处理保存
	 * //toid:评论的文章  pid:回复的目标  text:回复的内容
	 * @param toId
	 * @param pid
	 * @param text
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/comment/submit")
	public Object submit(Long toId,Long pid,String text) {
		System.out.println(toId+"---"+pid+"----"+text);
		return commentService.save(toId, pid, text);
	}
	/**
	 * 某篇文章下的所有评论
	 * @param articleId
	 * @param pageSize
	 * @param pn
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/comment/list/{articleId}")
	public Object list(@PathVariable Long articleId,int pageSize,int pn) {
		//System.out.println(commentService.list(articleId, pageSize, pn));
		return commentService.list(articleId, pageSize, pn).get("data");
	}
	//删除某篇文章下自己的评论
	@ResponseBody
	@RequestMapping("/comment/delete")
	public Object delete(long id,HttpServletRequest request) {
		return commentService.delete(id);
	}
}
