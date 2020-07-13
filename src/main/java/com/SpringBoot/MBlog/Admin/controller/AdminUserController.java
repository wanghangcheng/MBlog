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

import com.SpringBoot.MBlog.Admin.mapper.UserMapper;
import com.SpringBoot.MBlog.Admin.service.AdminUserService;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.util.LoginUtil;
import com.SpringBoot.MBlog.util.PageUtils;
import com.SpringBoot.MBlog.vo.Result;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
	
	@Autowired AdminUserService adminUserService;
	@Autowired UserMapper userMapper;
	/**
	 * 创建用户管理页面
	 * @param request
	 * @return
	 */
	@GetMapping("/users/list")
	public Object ShowArticle () {
		User loginUser = LoginUtil.getLoginUser();
		if(loginUser==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("/admin/user/admin_user");
		return mv;
	}
	/**
	 * 获取用户列表数据
	 * @param pageNumber
	 * @param pageSize
	 * @param sort
	 * @param order
	 * @param nickname
	 * @param beginDateTime
	 * @param endDateTime
	 * @param state
	 * @param role
	 * @return
	 */
	@PostMapping("/users/list")
	@ResponseBody
	public PageUtils Users (@RequestParam int pageNumber,int pageSize,String sort,String order,String nickname,String beginDateTime,String endDateTime,Integer state,Integer role) {
		if(sort.equals("createTime")) {
			sort="create_time";
		}
		//System.out.println("state:"+state+"role:"+role);
		String querySort = sort+" "+order;
		//list<map>适用于没有实体类时，又需要count字段的联合查询，搭配mybatis的collection
		List<Map<String, Object>> user_list = adminUserService.findUsers(pageNumber, pageSize, querySort, nickname, beginDateTime, endDateTime, state, role);
		//System.out.println(user_list.get(0));
		//List<Article> article_list= articleService.findArticles(pageNumber, pageSize,querySort,nickname,beginDateTime,endDateTime);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(user_list);
		int total =(int) pageInfo.getTotal();
		PageUtils pageUtils = new PageUtils(user_list, total);
		return pageUtils;
	}
	//修改用户
	@PostMapping("/user/edit")
	@ResponseBody
	public Result editUser(String userId,String username,String email,String state,String role) {
		//System.out.println(userId+" "+username+" "+email+" "+state+" "+role);
		Long u_id =Long.valueOf(userId);
		Integer u_state = Integer.valueOf(state);
		Integer u_role = Integer.valueOf(role);
		return adminUserService.editUser(u_id, username, email, u_state, u_role);
	}
	//删除用户
	@GetMapping("/user/delete")
	@ResponseBody
	public Result delUser(Long id) {
		return adminUserService.delUser(id);
	}
}
