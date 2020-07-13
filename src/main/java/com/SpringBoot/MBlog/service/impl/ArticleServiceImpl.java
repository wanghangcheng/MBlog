package com.SpringBoot.MBlog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBoot.MBlog.dao.ArticleDao;
import com.SpringBoot.MBlog.dao.ArticleTagDao;
import com.SpringBoot.MBlog.dao.CommentDao;
import com.SpringBoot.MBlog.dao.ComplaintDao;
import com.SpringBoot.MBlog.dao.FavorDao;
import com.SpringBoot.MBlog.dao.FollowsDao;
import com.SpringBoot.MBlog.dao.ForumDao;
import com.SpringBoot.MBlog.dao.NotifyDao;
import com.SpringBoot.MBlog.dao.TagDao;
import com.SpringBoot.MBlog.dao.UserDao;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.ArticleTag;
import com.SpringBoot.MBlog.entity.Comment;
import com.SpringBoot.MBlog.entity.Complaint;
import com.SpringBoot.MBlog.entity.Favor;
import com.SpringBoot.MBlog.entity.Follows;
import com.SpringBoot.MBlog.entity.Forum;
import com.SpringBoot.MBlog.entity.Notify;
import com.SpringBoot.MBlog.entity.Tag;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.service.ArticleService;
import com.SpringBoot.MBlog.util.EhcacheUtil;
import com.SpringBoot.MBlog.util.IPUtil;
import com.SpringBoot.MBlog.util.LoginUtil;
import com.SpringBoot.MBlog.vo.ArticleVo;
import com.SpringBoot.MBlog.vo.HitUtilVo;
import com.SpringBoot.MBlog.vo.Result;
import com.SpringBoot.MBlog.vo.UserVo;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired UserDao userDao;
	@Autowired ForumDao forumDao;
	@Autowired ArticleDao articleDao;
	@Autowired FavorDao favorDao;
	@Autowired FollowsDao followsDao;
	@Autowired NotifyDao notifyDao;
	@Autowired CommentDao commtentDao;
	@Autowired TagDao tagDao;
	@Autowired ArticleTagDao articleTagDao;
	@Autowired ComplaintDao complaintDao;
	
	private static 	List<HitUtilVo> hitList = new ArrayList<>();
	
	//文章发表
	@Override
	public Result save(String title, long group, String content, String tags, HttpServletRequest request) {
		HttpSession session =  request.getSession();
		User loginUser = (User)session.getAttribute("loginInfo");
		
		User user = userDao.getOne(loginUser.getId());
		Forum forum = forumDao.getOne(group);
		
		Article article = new Article();
		article.setContent(content);
		article.setCreateTime(new Date());
		article.setForum(forum);
		article.setHits(0L);
		article.setTitle(title);
		article.setUser(user);
		article.setCream(0);
		article.setTop(0);
		//System.out.println(article.toString());
		articleDao.save(article);
		
		//标签相关
		if(!StringUtils.isEmpty(tags)) {
			//按,把标签切割
			String[] tagArr = tags.split(",");
			for(String tagName:tagArr) {
				Tag tag = tagDao.findByName(tagName);
				if(tag==null) {
					tag = new Tag();
					tag.setName(tagName);
					tagDao.save(tag);
				}
				ArticleTag aTag = new ArticleTag();
				aTag.setArticle(article);
				aTag.setTag(tag);
				aTag.setCreate_time(article.getCreateTime());
				articleTagDao.save(aTag);
			}
		}
		
		/**
		 * 给粉丝发动态
		 */
		Integer countFans = followsDao.countFans(loginUser.getId());
		//System.out.println(countFans);
		if (countFans!=0) {
			Sort sort = new Sort(Direction.DESC,"created");
			Pageable pageable = new PageRequest(0, countFans,sort);
			Page<Follows> data = followsDao.findFans(loginUser.getId(), pageable);
			for(int i=0;i<countFans;i++) {
				Notify notify = new Notify();
				notify.setAvatar(user.getAvatar());
				String s2 = String.format("发布了一篇文章 - <a href = \"/view/%s.html\">《%s》</a>", article.getId(),article.getTitle());
				notify.setContent(s2);
				notify.setCreated(new Date());
				notify.setTitle(user.getNickname());
				notify.setStaus(1);
				notify.setUrl("/ta/"+loginUser.getId());
				User fans = data.getContent().get(i).getSource();
				notify.setUser(fans);
				notifyDao.save(notify);
			}
			
		}
		return null;
	}
	/**
	 * 先清除缓存，再查询文章
	 */
	@CacheEvict(value="findArticle",key="#p0")
	public void view(long articleId,ModelAndView mv, HttpServletRequest request) {
		
		Article article = articleDao.findById(articleId);
		//设置判断条件
		boolean hitFlag = true;
		//获取客户端ip
		String ipHost = IPUtil.getRealIp(request);
		HitUtilVo hVo = new HitUtilVo();
		hVo.setUserIP(ipHost);
		hVo.setArticleId(articleId);
		//创建ip的list集合通过获取的ip进行比对有存在将flag设为false不修改hit值
		for(HitUtilVo h : hitList) {
			if(h.getUserIP().equals(hVo.getUserIP())&&h.getArticleId()==hVo.getArticleId()) {
				
				hitFlag =false;
			}
		}
		//如果同一ip同一天对同一文章进行访问为false不增加Hit
		if(hitFlag) {
			//浏览量
			//System.out.println("addhit");
			Long hits= article.getHits()+1;
			article.setHits(hits);
			articleDao.updateHit(hits, articleId);
			hitList.add(hVo);
		}
		
		User user = article.getUser(); 
		ArticleVo articleVo = new ArticleVo();
		UserVo userVo = new UserVo();
		BeanUtils.copyProperties(article, articleVo);
		BeanUtils.copyProperties(user, userVo);
		//查询用户发表的文章数和评论数
		int articleCount = articleDao.countByUser(user.getId());
		int commtentCount = commtentDao.countByUser(user.getId());
		
		userVo.setArticleCount(articleCount);
		userVo.setCommtentCount(commtentCount);
		
		//查询喜欢数，设置10秒缓存
		String key2="a_like"+article.getId();
		Integer favorCount = EhcacheUtil.get("mytest", key2);
		if(favorCount==null) {
			favorCount = favorDao.countByArticle(article.getId());//通过文章id查询喜欢数
			EhcacheUtil.put("mytest", key2, favorCount,10,10);
		}
		
		//查询文章标签集
		List<Tag> tags = articleTagDao.findTagsByArticle(articleVo.getId());
		articleVo.setTags(new ArrayList<String>());
		tags.forEach(tag->{
			articleVo.getTags().add(tag.getName());
		});
		
		articleVo.setLike(favorCount);
		articleVo.setUser(userVo);
		
		mv.addObject("articleInfo",articleVo);
		
	}
	/**
	 * 获取文章
	 */
	@Override
	public Article getArticleById(long articleId) {
		//System.out.println(articleId);
		return articleDao.getOne(articleId);
	}
	/**
	 * 删除文章
	 */
	@Override
	public Result delete(long id) {
		//删除文章前，删除喜欢，删除评论
		Pageable pageable = new PageRequest(1, Integer.MAX_VALUE);
		List<Comment> commentlist = commtentDao.findByArticleId(id, pageable).getContent();
		commtentDao.deleteInBatch(commentlist);
		List<Favor> favorlist = favorDao.favorByArticleId(id);
		favorDao.deleteInBatch(favorlist);
		articleDao.delete(id);
		return Result.of(3, "删除文章成功");
	}

	@Override
	public void to_update(long articleId, ModelAndView mv) {
		
		User loginUser = LoginUtil.getLoginUser();
		Article article = articleDao.find(loginUser.getId(), articleId);
		List<Tag> tags = articleTagDao.findTagsByArticle(articleId);
		article.setTags(new ArrayList<String>());
		tags.forEach(tag->{
			article.getTags().add(tag.getName());
		});
		//System.out.println(article.getTags());
		mv.addObject("articleInfo",article);
		
	}
	//文章更新，先将articleTags的#p0缓存清空，然后执行先查后改的原则
	@CacheEvict(value="articleTags",key="#p0")
	public void update(long articleId, String title, String content, long group, String tags, ModelAndView mv) {
		
		Article article =articleDao.getOne(articleId);
		article.setTitle(title);
		article.setContent(content);
		Forum forum = forumDao.getOne(group);
		article.setForum(forum);
		//删除标签相关
		articleTagDao.deleteInBatch(articleTagDao.findByArticleId(articleId));
		
		//标签相关
		if(!StringUtils.isEmpty(tags)) {
			//按,把标签切割
			String[] tagArr = tags.split(",");
			for(String tagName:tagArr) {
				Tag tag = tagDao.findByName(tagName);
				if(tag==null) {
					tag = new Tag();
					tag.setName(tagName);
					tagDao.save(tag);
				}
				ArticleTag aTag = new ArticleTag();
				aTag.setArticle(article);
				aTag.setTag(tag);
				aTag.setCreate_time(article.getCreateTime());
				articleTagDao.save(aTag);
			}
		}
		articleDao.save(article);
		mv.setViewName("redirect:/home");
	}
	/**
	 * 通过标签查询同一标签下的文章信息
	 */
	@Override
	public void tag(String tags, int page,ModelAndView mv) {
		Sort sort = new Sort(Direction.DESC,"create_time");
		Pageable pageable = new PageRequest(page-1,7,sort);
		
		Tag tag = tagDao.findByName(tags);
		Page<Article> data = articleTagDao.articlesFindTagId(tag.getId(),pageable);
		mv.addObject("pageInfo",data);//主要目的，将分页信息传到页面，虽然有文章信息但是不用
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
			int count = commtentDao.countByArticleId(act.getId());//通过文章id查询评论数
			act.setComment(count);
			act.setLike(favorCount);
			//文章标签
			List<Tag> t = articleTagDao.findTagsByArticle(act.getId());
			act.setTags(new ArrayList<String>());
			t.forEach(ta->{
				act.getTags().add(ta.getName());
			});
		}); 
	}
	/**
	 * 对文章字符串中的特殊字符进行替换，防止页面崩溃
	 * @param str
	 * @return
	 */
	public static String delHtmlTag(String str) {
		
		String newstr = "";
		newstr = str.replaceAll("<[.[^>]]*>", "");
		newstr = newstr.replaceAll(" ", "");
		return newstr;
	}	
	/**
	 * 定时任务每天凌晨1点清除list集合
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public static void delHitList() {
		//System.out.println("clear:"+new Date());
		hitList.clear();
	}
	/**
	 * 文章举报
	 */
	@Override
	public Result ArtComplaint(Long artId, Integer genre, String remarks) {
		
		Complaint complaint = new Complaint();
		complaint.setTarget_art(articleDao.getOne(artId));
		complaint.setGenre(genre);//投诉类别
		complaint.setCreated(new Date());
		complaint.setRemarks(remarks);
		complaint.setType(1);//投诉类型：1：文章，0：用户
		//System.out.println(complaint.toString());
		complaint.setHandle(false);//未处理
		complaintDao.save(complaint);
		return Result.of(101,"反馈成功");
		
	}
}
