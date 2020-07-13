package com.SpringBoot.MBlog.Admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.Admin.service.AdminArtService;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.util.PageUtils;
import com.SpringBoot.MBlog.vo.Result;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminArtController {
	
	@Autowired AdminArtService articleService;
	
	/**
	 * 后台管理页面生成
	 * @param mv
	 * @return
	 */
	@RequestMapping("/home")
	public Object admin (HttpServletRequest request) {
		HttpSession session =  request.getSession();
		User loginUser = (User)session.getAttribute("loginInfo");
		if(loginUser==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("/admin/admin");
		return mv;
	}
	/**
	 * 创建文章页面
	 * @param request
	 * @return
	 */
	@GetMapping("/article/list")
	public Object ShowArticle (HttpServletRequest request) {
		HttpSession session =  request.getSession();
		User loginUser = (User)session.getAttribute("loginInfo");
		if(loginUser==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("/admin/article/admin_article");
		return mv;
	}
	/**
	 * 根据table传入参数查询数据列表返回，因字段createTime不再使用故要转成create_time
	 * @param pageNumber
	 * @param pageSize
	 * @param sort
	 * @param order
	 * @param nickname
	 * @param beginDateTime
	 * @param endDateTime
	 * @return
	 */
	@PostMapping("/article/list")
	@ResponseBody
	public PageUtils Article (@RequestParam int pageNumber,int pageSize,String sort,String order,String nickname,String artTitle,String beginDateTime,String endDateTime) {
		if(sort.equals("createTime")) {
			sort="create_time";
		}
		String querySort = sort+" "+order;
		List<Article> article_list= articleService.findArticles(pageNumber, pageSize,querySort,nickname,artTitle,beginDateTime,endDateTime);
		PageInfo<Article> pageInfo = new PageInfo<>(article_list);
		int total =(int) pageInfo.getTotal();
		PageUtils pageUtils = new PageUtils(article_list, total);
		return pageUtils;
	}
	/**
	 * 修改文章状态（加精、置顶）
	 * @param artId
	 * @param top
	 * @param cream
	 * @return
	 */
	@PostMapping("/article/edit")
	@ResponseBody
	public Result editArt(String artId,String top,String cream) {
		
		Long id = Long.valueOf(artId);
		Integer topInteger = Integer.valueOf(top);
		Integer creamInteger = Integer.valueOf(cream);
		
		return articleService.editArt(id, topInteger, creamInteger);
	}
	/**
	 * 管理员删除某篇文章
	 * @param id
	 * @return
	 */
	@GetMapping("/article/delete")
	@ResponseBody
	public Object adminDelArt(Long id) {
		return articleService.adminDelArt(id);
	}
}
