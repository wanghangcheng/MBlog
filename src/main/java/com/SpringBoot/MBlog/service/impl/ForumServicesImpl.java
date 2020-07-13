package com.SpringBoot.MBlog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.Admin.mapper.ArticleMapper;
import com.SpringBoot.MBlog.dao.ArticleDao;
import com.SpringBoot.MBlog.dao.ArticleTagDao;
import com.SpringBoot.MBlog.dao.CommentDao;
import com.SpringBoot.MBlog.dao.FavorDao;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.Tag;
import com.SpringBoot.MBlog.service.ForumService;


@Service
public class ForumServicesImpl implements ForumService {

	@Autowired ArticleDao articleDao;
	@Autowired CommentDao commentDao;
	@Autowired FavorDao favorDao;
	@Autowired ArticleTagDao articleTagDao; 
	//首页查询
	@Autowired ArticleMapper articleMapper;
	
	@Override
	public void findForumArtives(String forumCode, String sortType, int page,ModelAndView mv) {
		
		Sort sort=null;
		if(sortType==null||sortType.trim().length()==0){
			sort = new Sort(Direction.DESC,"top").and(new Sort(Direction.DESC,"createTime"));
		}else if ("newest".equals(sortType)) {
			sort = new Sort(Direction.DESC,"top").and(new Sort(Direction.DESC,"createTime"));
		}else if ("hottest".equals(sortType)) {
			sort = new Sort(Direction.DESC,"top").and(new Sort(Direction.DESC,"hits"));
		}
		Pageable pageable = new PageRequest(page-1,7, sort);
		Page<Article> data = articleDao.findByForumCode(forumCode,pageable);
		
		mv.addObject("pageInfo",data);
		data.getContent().forEach(act->{
			String str = delHtmlTag(act.getContent());
			if(str.length()>200) {
				str = str.substring(0,200);
			}
			else {
				str = str.substring(0,str.length());
			}
			act.setContent(str);
			//查询喜欢数，设置10秒缓存
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
		
	}
	public static String delHtmlTag(String str) {
		
		String newstr = "";
		newstr = str.replaceAll("<[.[^>]]*>", "");
		newstr = newstr.replaceAll(" ", "");
		return newstr;
	}
	
	/**
	 * 首页文章数据
	 */
	@Override
	public void findHomeArt(ModelAndView mv) {
		
		List<Article> AllList = new ArrayList<>();
		
		List<Article> creamList = articleMapper.findHomeArtByCream(3);
		List<Article> hitsList = articleMapper.findHomeArtByHits(3);
		creamList.forEach(action->{
			action.setType(0);
			AllList.add(action);
		});
		hitsList.forEach(action->{
			action.setType(1);
			AllList.add(action);
		});
		mv.addObject("AllList", AllList);
		//System.out.println(AllList.get(0).toString()+"\n"+AllList.get(5).toString());
	}
}
