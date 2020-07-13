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

import com.SpringBoot.MBlog.Admin.service.AdminJournalService;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.util.LoginUtil;
import com.SpringBoot.MBlog.util.PageUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminJournalController {
	
	@Autowired AdminJournalService adminJournalService;
	
	/**
	 * 创建日志界面
	 * @return
	 */
	@GetMapping("/journal/list")
	public Object ShowJournal () {
		User loginUser = LoginUtil.getLoginUser();
		if(loginUser==null) {
			return "redirect:/login.html";
		}
		ModelAndView mv = new ModelAndView("/admin/journal/admin_journal");
		return mv;
	}	
	@PostMapping("/journal/list")
	@ResponseBody
	public PageUtils Journals (@RequestParam int pageNumber,int pageSize,String sort,String order,String username,String type) {
		String querySort = sort+" "+order;
		//list<map>适用于没有实体类时，又需要count字段的联合查询，搭配mybatis的collection
		List<Map<String, Object>> journal_list = adminJournalService.findJournals(pageNumber, pageSize, querySort, order, username, type);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(journal_list);
		int total =(int) pageInfo.getTotal();
		PageUtils pageUtils = new PageUtils(journal_list, total);
		return pageUtils;
	}
}
