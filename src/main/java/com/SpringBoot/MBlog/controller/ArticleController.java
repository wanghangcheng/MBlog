package com.SpringBoot.MBlog.controller;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.service.ArticleService;
import com.SpringBoot.MBlog.vo.Result;

/**
 * 写文章相关控制器
 * @author hangcheng
 *
 */
@Controller
public class ArticleController {
	
	@Autowired ArticleService articleService;
	/**
	 * 创建版块里文章展示页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/article/create",method=RequestMethod.GET)
	public Object create(HttpServletRequest request) {
		HttpSession session =  request.getSession();
		User loginUser = (User)session.getAttribute("loginInfo");
		if(loginUser==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("article/create");
		return mv;
	}
	/**
	 * 文章提交请求
	 * @param title
	 * @param group
	 * @param context
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/article/create",method=RequestMethod.POST)
	public Object create(String title,long group,String content,String tags,HttpServletRequest request ) {
		
		HttpSession session =  request.getSession();
		User loginUser = (User)session.getAttribute("loginInfo");
		if(loginUser==null) {
			return "redirect:/login.html";
		}
		System.out.println(111);
		articleService.save(title, group, content, tags, request);
		
		return "redirect:/home";
	}
	/**
	 * 文章详情展示请求控制
	 * @param title
	 * @param group
	 * @param context
	 * @param request
	 * @return
	 * @throws UnknownHostException 
	 */
	@RequestMapping("/view/{articleId}.html")
	public Object view(@PathVariable long articleId ,HttpServletRequest request,ModelAndView mv) throws UnknownHostException {
		mv.setViewName("article/view");
		articleService.view(articleId,mv,request);
//		获取本地ip
//		InetAddress addr = InetAddress.getLocalHost();
//		String addrt = addr.getHostAddress();
		return mv;
	}
	
	/**删除自己的某篇文章
	 * @param id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/post/delete")
	public Object delete(long id,HttpServletRequest request) {
		return articleService.delete(id);
	}
	
	/**
	 * 修改自己的某篇文章
	 */
	@ResponseBody
	@RequestMapping("/post/to_update/{articleId}")
	public Object to_update(@PathVariable long articleId,ModelAndView mv) {
		
		mv.setViewName("article/update");
		articleService.to_update(articleId,mv);
		return mv;
	}
	
	@RequestMapping("/article/update")
	public Object to_update( long id,String title,String content,long group,String tags, ModelAndView mv) {
		
		mv.setViewName("article/update");
		articleService.update(id,title,content,group,tags,mv);
		return mv;
	}
	/**
	 * 标签索引
	 */
	@RequestMapping("/tag/ord={tags}")
	public Object tag( @PathVariable String tags,@RequestParam(defaultValue="1")int page, ModelAndView mv,HttpServletRequest request) {
		
		mv.setViewName("article/tags");
		articleService.tag(tags,page,mv);
		return mv;
	}
	@PostMapping(value = "/article/complaint")
	@ResponseBody
	public Result ArtComplaint(String artId,String type,String remarks) {
		//System.out.println(artId+" "+type+" "+remarks);
		return articleService.ArtComplaint(Long.valueOf(artId), Integer.valueOf(type), remarks);
	}

}
