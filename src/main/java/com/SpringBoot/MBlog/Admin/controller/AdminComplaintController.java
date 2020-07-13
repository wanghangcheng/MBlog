package com.SpringBoot.MBlog.Admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.Admin.service.AdminComplaintService;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.util.LoginUtil;
import com.SpringBoot.MBlog.util.PageUtils;
import com.SpringBoot.MBlog.vo.Result;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminComplaintController {
	
	@Autowired AdminComplaintService adminComplaintService;
	
	/**
	 * 创建文章投诉管理页面
	 * @param request
	 * @return
	 */
	@GetMapping("/complaint/article/list")
	public Object ShowArticle () {
		User loginUser = LoginUtil.getLoginUser();
		if(loginUser==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("/admin/complaint/admin_complaint_article");
		return mv;
	}
	/**
	 * 创建用户投诉管理页面
	 * @param request
	 * @return
	 */
	@GetMapping("/complaint/user/list")
	public Object ShowUser () {
		User loginUser = LoginUtil.getLoginUser();
		if(loginUser==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("/admin/complaint/admin_complaint_user");
		return mv;
	}
	/**
	 * 查询文章投诉列表
	 * @param pageNumber
	 * @param pageSize
	 * @param sort
	 * @param order
	 * @param art_title
	 * @param handle
	 * @return
	 */
	@PostMapping("/complaint/article/list")
	@ResponseBody
	public PageUtils complaint_Art (@RequestParam int pageNumber,int pageSize,String sort,String order,String art_title,String handle) {
		String querySort = sort+" "+order;
		//list<map>适用于没有实体类时，又需要count字段的联合查询，搭配mybatis的collection
		List<Map<String, Object>> art_list = adminComplaintService.findComplaintArts(pageNumber, pageSize, querySort, art_title,handle);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(art_list);
		int total =(int) pageInfo.getTotal();
		PageUtils pageUtils = new PageUtils(art_list, total);
		return pageUtils;
	}
	@PostMapping("/complaint/user/list")
	@ResponseBody
	public PageUtils complaint_User (@RequestParam int pageNumber,int pageSize,String sort,String order,String user_nickname,String handle) {
		String querySort = sort+" "+order;
		//list<map>适用于没有实体类时，又需要count字段的联合查询，搭配mybatis的collection
		List<Map<String, Object>> user_list = adminComplaintService.findComplaintUsers(pageNumber, pageSize, querySort, user_nickname, handle);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(user_list);
		int total =(int) pageInfo.getTotal();
		PageUtils pageUtils = new PageUtils(user_list, total);
		return pageUtils;
	}
	/**
	 * 修改处理结果
	 * @param complaintId
	 * @param handle
	 * @return
	 */
	@PostMapping("/complaint/edit")
	@ResponseBody
	public Result editComplaint_Handle(String Id,String content,String type) {
		//System.out.println(complaintId+" "+handle);
		Long id = Long.valueOf(Id);
		Integer t = Integer.valueOf(type);
		return adminComplaintService.editHandle(id, content,t);
	}
}
