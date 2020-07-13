package com.SpringBoot.MBlog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.dao.ArticleDao;
import com.SpringBoot.MBlog.dao.ArticleTagDao;
import com.SpringBoot.MBlog.dao.CommentDao;
import com.SpringBoot.MBlog.dao.FavorDao;
import com.SpringBoot.MBlog.dao.UserDao;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.Tag;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.vo.UserVo;

@Controller
public class SearchController {
	
	@Autowired ArticleDao articleDao;
	@Autowired UserDao userDao;
	@Autowired CommentDao commentDao;
	@Autowired FavorDao favorDao;
	@Autowired ArticleTagDao articleTagDao;
	
	//查询相关文章
	@RequestMapping("/search")
	public Object f1(String q,@RequestParam(defaultValue="1") int page) {
		
		ModelAndView mv = new ModelAndView("searchResult");
		Sort sort = new Sort(Direction.DESC,"createTime");
		Pageable pageable = new PageRequest(page-1, 7,sort);
		Page<Article> pageInfo = articleDao.search(q, pageable);
		mv.addObject("pageInfo", pageInfo);
		mv.addObject("articleList", pageInfo.getContent());
		//查询喜欢数，设置10秒缓存
		pageInfo.getContent().forEach(act->{
			int favorCount = favorDao.countByArticle(act.getId());//通过文章id查询喜欢数
			int count = commentDao.countByArticleId(act.getId());//通过文章id查询评论数
			act.setComment(count);
			act.setLike(favorCount);
			//文章标签
			List<Tag> tags = articleTagDao.findTagsByArticle(act.getId());
			act.setTags(new ArrayList<String>());
			tags.forEach(tag->{
				act.getTags().add(tag.getName());
			});
		});
		
		return mv;
	}
	
	//查询相关用户
	@RequestMapping("/search/user")
	public Object f2(String q,@RequestParam(defaultValue="1") int page) {
		ModelAndView mv = new ModelAndView("searchResult2");
		Pageable pageable = new PageRequest(page-1, 7);
		Page<User> pageInfo =  userDao.search(q, pageable);
		
		List<UserVo> list = new ArrayList<UserVo>();
		for(User user: pageInfo.getContent()) {//循环获取用户
			
			UserVo userVo = new UserVo();
			BeanUtils.copyProperties(user, userVo);//将用户拷贝
			
			//查询用户发表的文章数和评论数
			int articleCount = articleDao.countByUser(user.getId());
			int commtentCount = commentDao.countByUser(user.getId());
			
			userVo.setArticleCount(articleCount);
			userVo.setCommtentCount(commtentCount);
			list.add(userVo);
			
		}
		
		mv.addObject("pageInfo", pageInfo);//分页数据
		mv.addObject("userList",list);//数据列表

		return mv;
	}
}
