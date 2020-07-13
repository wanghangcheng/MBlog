package com.SpringBoot.MBlog.Admin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.MBlog.Admin.mapper.ArticleMapper;
import com.SpringBoot.MBlog.Admin.mapper.ForumMapper;
import com.SpringBoot.MBlog.Admin.mapper.JournalMapper;
import com.SpringBoot.MBlog.Admin.mapper.NotifyMapper;
import com.SpringBoot.MBlog.Admin.service.AdminForumService;
import com.SpringBoot.MBlog.entity.Admin_Journal;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.Forum;
import com.SpringBoot.MBlog.entity.Notify;
import com.SpringBoot.MBlog.util.LoginUtil;
import com.SpringBoot.MBlog.vo.Result;
import com.github.pagehelper.PageHelper;

@Service
public class AdminForumServiceImpl implements AdminForumService {
	
	@Autowired ForumMapper forumMapper;
	@Autowired ArticleMapper articleMapper;
	@Autowired NotifyMapper notifyMapper;
	@Autowired JournalMapper journalMapper;
	@Autowired
    private ServletContext servletContext;//注入上下文容器
	
	/**
	 * 使用list<map>返回查询到的所有版块信息
	 */
	public List<Map<String, Object>> findForums(int pageNumber,int pageSize,String sort,String name,String status) {
		
		PageHelper.startPage(pageNumber, pageSize, sort);
		List<Map<String, Object>> list = forumMapper.findForumVos(name,status);
		
		return list;
	}
	/**
	 * 修改版块信息（博文版块不修改作为主页）
	 * 先查再改再存
	 * 再更新上下文容器中FORUM_LIST里的信息，防止前台信息错误
	 */
	public Result editFotum(Long f_id, String f_name, Integer f_status, Integer weight) {
		
		Forum old_forum = forumMapper.findForumById(f_id);
		//通过weight、name查询是否存在，避免出现权重一致影响排序
		Forum weight_forum = forumMapper.findForumByWeight(weight);
		Forum name_forum= forumMapper.findForumByName(f_name);
		//控制前台板块显示数
		Integer statusCount = forumMapper.findStatusCount((f_status==1)?1:0);
		if(f_status==1&&statusCount==4&&f_id!=old_forum.getId()) {
			return Result.of(104, "显示数超标，只允许同时显示4个板块");
		}
		if(weight_forum!=null&&weight_forum.getId()!=old_forum.getId()) {
			return Result.of(101, "该权重值已存在，请重新选择");
		}
		if(name_forum!=null&&name_forum.getId()!=old_forum.getId()) {
			return Result.of(105, "该名称板块已存在，请重新选择");
		}
		old_forum.setName(f_name);
		old_forum.setStatus((f_status==1)?true:false);
		old_forum.setWeight(weight);
		//保存操作到日志
		Admin_Journal admin_Journal = new Admin_Journal();
		admin_Journal.setCreated(new Date());
		admin_Journal.setSource_user(LoginUtil.getLoginUser());
		String s2 = String.format("管理员帐号：%s对栏目名称为：%s的板块的信息进行了修改操作", LoginUtil.getLoginUser().getUsername(),old_forum.getName());
		admin_Journal.setContent(s2);
		admin_Journal.setType(2);
		journalMapper.insertJournal(admin_Journal);
		Integer flag = forumMapper.editForum(old_forum);
		
		//修改后重新查询forum显示列表，放回上下文中，防止前台不一致
		List<Forum> list = forumMapper.findListByStatus(1);
		servletContext.setAttribute("FORUM_LIST",list);
		if(flag==0) {
			return Result.of(102,"出现错误，更新失败");
		}
		return Result.of(103, "修改成功");
	}
	/**
	 * 删除前先把该版块下文章全转移到首页（博文底下）
	 * 并通知每个作者
	 */
	public Result delForumById(Long id) {
		//先查防止首页版块被删
		Forum old_forum = forumMapper.findForumById(id);
		if(old_forum.getWeight()==1) {
			Result.of(103,"首页板块无法删除");
		}
		//获取该板块下所有文章
		List<Article> articles_forum = articleMapper.findArticlesByForumId(old_forum.getId());
		//将该版块下文章转至blog板块
		Integer editArticleForum = articleMapper.EditArticlesByForumId(old_forum.getId());
		//返回值不为0表明有修改，要给作者发通知
		if(editArticleForum!=0) {
			for(Article article : articles_forum) {
				Notify notify = new Notify();
				notify.setAvatar(LoginUtil.getLoginUser().getAvatar());
				String s2 = String.format("您的文章《%s》，因为版块移除已转移到博文版块下，如有需要更改，可到个人中心修改。",article.getTitle());
				notify.setContent(s2);
				notify.setCreated(new Date());
				notify.setTitle(LoginUtil.getLoginUser().getNickname());
				notify.setStaus(0);
				notify.setUrl("/ta/"+LoginUtil.getLoginUser().getId());
				notify.setUser(article.getUser());
				notifyMapper.InsertDelArt(notify);
			}
		}
		
		//保存操作到日志
		Admin_Journal admin_Journal = new Admin_Journal();
		admin_Journal.setCreated(new Date());
		admin_Journal.setSource_user(LoginUtil.getLoginUser());
		String s2 = String.format("管理员帐号：%s对栏目名称为：%s的板块的信息进行了删除操作", LoginUtil.getLoginUser().getUsername(),old_forum.getName());
		admin_Journal.setContent(s2);
		admin_Journal.setType(1);
		journalMapper.insertJournal(admin_Journal);
		
		Integer flag = forumMapper.delForumById(old_forum.getId());
		if(flag==0) {
			return Result.of(101,"删除失败");
		}
		return Result.of(100, "删除成功");
	}
	/**
	 * 新增版块
	 */
	@Override
	public Result insertForum(String name, String code, Integer weight) {
		//先查询是否存在相同code或weight或name的版块
		Forum weight_forum = forumMapper.findForumByWeight(weight);
		Forum name_forum= forumMapper.findForumByName(name);
		Forum code_forum= forumMapper.findForumByCode(code);
		if(code_forum!=null) {
			return Result.of(102, "该版块标识已存在，请重新选择");
		}
		if(weight_forum!=null) {
			return Result.of(101, "该权重值已存在，请重新选择");
		}
		if(name_forum!=null) {
			return Result.of(103, "该名称板块已存在，请重新选择");
		}
		Forum n_forum = new Forum();
		n_forum.setCode(code);
		n_forum.setName(name);
		n_forum.setWeight(weight);
		n_forum.setStatus(false);
		
		//保存操作到日志
		Admin_Journal admin_Journal = new Admin_Journal();
		admin_Journal.setCreated(new Date());
		admin_Journal.setSource_user(LoginUtil.getLoginUser());
		String s2 = String.format("管理员帐号：%s新增了一个栏目名称为：%s的板块", LoginUtil.getLoginUser().getUsername(),n_forum.getName());
		admin_Journal.setContent(s2);
		admin_Journal.setType(0);
		journalMapper.insertJournal(admin_Journal);
		
		forumMapper.insertForum(n_forum);
		
		return Result.of(100, "添加成功");
	}
}
