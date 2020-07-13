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

import com.SpringBoot.MBlog.Admin.service.AdminForumService;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.util.LoginUtil;
import com.SpringBoot.MBlog.util.PageUtils;
import com.SpringBoot.MBlog.vo.Result;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminForumController {
	
	@Autowired AdminForumService adminForumService ;
	
	/**
	 * 创建栏目管理页面
	 * @param request
	 * @return
	 */
	@GetMapping("/forum/list")
	public Object ShowForum () {
		User loginUser = LoginUtil.getLoginUser();
		if(loginUser==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("/admin/forum/admin_forum");
		return mv;
	}
	/**
	 * 获取板块列表
	 * @param pageNumber
	 * @param pageSize
	 * @param sort
	 * @param order
	 * @param name
	 * @param status
	 * @return
	 */
	@PostMapping("/forum/list")
	@ResponseBody
	public PageUtils Forums (@RequestParam int pageNumber,int pageSize,String sort,String order,String name,String status) {
		String querySort = sort+" "+order;
		//list<map>适用于没有实体类时，又需要count字段的联合查询，搭配mybatis的collection
		List<Map<String, Object>> forum_list = adminForumService.findForums(pageNumber, pageSize, querySort, name, status);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(forum_list);
		int total =(int) pageInfo.getTotal();
		PageUtils pageUtils = new PageUtils(forum_list, total);
		return pageUtils;
	}
	/**
	 * 修改版块信息
	 * @param forumId
	 * @param name
	 * @param stats
	 * @param weight
	 * @return
	 */
	@PostMapping("/forum/edit")
	@ResponseBody
	public Result editUser(String forumId,String name,String stats,String weight) {
		//System.out.println(forumId+" "+name+" "+stats+" "+weight);
		Long f_id =Long.valueOf(forumId);
		Integer f_status = Integer.valueOf(stats);
		//System.out.println(f_status);
		Integer f_weight = Integer.valueOf(weight);
		return adminForumService.editFotum(f_id, name, f_status, f_weight);
	}
	/**
	 * 删除某版块
	 * @param id
	 * @return
	 */
	@GetMapping("/forum/delete")
	@ResponseBody
	public Result delForum(Long id) {
		return adminForumService.delForumById(id);
	}
	/**
	 * 新增板块
	 * @param f_n_name
	 * @param f_n_code
	 * @param f_n_weight
	 * @return
	 */
	@PostMapping("/forum/insert")
	@ResponseBody
	public Result insertForum(String f_n_name,String f_n_code,String f_n_weight) {
		//System.out.println(f_n_name+" "+f_n_code+" "+f_n_weight);
		return adminForumService.insertForum(f_n_name, f_n_code, Integer.valueOf(f_n_weight));
	}
}
