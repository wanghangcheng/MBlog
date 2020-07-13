package com.SpringBoot.MBlog.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.SpringBoot.MBlog.dao.ArticleDao;
import com.SpringBoot.MBlog.dao.FavorDao;
import com.SpringBoot.MBlog.dao.FollowsDao;
import com.SpringBoot.MBlog.dao.NotifyDao;
import com.SpringBoot.MBlog.dao.UserDao;
import com.SpringBoot.MBlog.entity.Article;
import com.SpringBoot.MBlog.entity.Favor;
import com.SpringBoot.MBlog.entity.Follows;
import com.SpringBoot.MBlog.entity.Notify;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.service.AccountService;
import com.SpringBoot.MBlog.util.DigestHelper;
import com.SpringBoot.MBlog.util.EmailUtil;
import com.SpringBoot.MBlog.util.LoginUtil;
import com.SpringBoot.MBlog.util.SendEmail;
import com.SpringBoot.MBlog.vo.Result;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class AccountSreviceImpl implements AccountService {
	
	@Autowired UserDao userDao;
	@Autowired FavorDao favorDao;
	@Autowired FollowsDao followsDao;
	@Autowired NotifyDao notifyDao;
	@Autowired ArticleDao articleDao;
	
	@Value("${STORE_ROOT_PATH}")
	String StoreRootPath;//存储根目录
	
	/**
	 * 更新密码
	 */
	@Override
	public Result updatePassword(String oldPassword, String newPassword) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session =  request.getSession();
		User loginUser = (User)session.getAttribute("loginInfo");
		
		User user = userDao.getOne(loginUser.getId());
		String m = oldPassword;    //明文密码
		String s = user.getSalt();//加密盐值
		String r = md5(sha512(m)+md5(s)+sha512(m+s));
		
		if(r.equals(user.getPassword())) {
			user.setSalt(UUID.randomUUID().toString());//改个新的盐值
			String m1 =newPassword;
			String s1 =user.getSalt();
			String r1 =md5(sha512(m1)+md5(s1)+sha512(m1+s1));
			user.setPassword(r1);
			
			userDao.save(user);
			System.out.println(user.getEmail()+"----------"+newPassword);
			//发送邮件通知
			Boolean falg = SendEmail.SendPassword(user.getEmail(),newPassword);
			if (falg) {
				return Result.of(200).put("msg", "修改密码成功");
			} else {
				return Result.of(800).put("msg", "邮件发送失败");
			}
			
		}else {

			return Result.of(300);
		}
	}
	private String md5(String text) {
		return DigestHelper.md5(text);
	}
	private String sha512(String text) {
		return DigestHelper.sha512(text);
	}
	/**
	 * 更新基本信息
	 */
	@Override
	public Result updateProfile(String nickname, String sign) {
		//System.out.println(nickname);
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session =  request.getSession();
		User loginUser = (User)session.getAttribute("loginInfo");
		
		User user = userDao.getOne(loginUser.getId());
		if(userDao.findByNickname(nickname)!=null) {
			return Result.of(102,"昵称已经存在，请更换");
		}
		notifyDao.UpdateTiTleOnUser(nickname, user.getNickname());
		user.setNickname(nickname);
		user.setSign(sign);
		userDao.save(user);
		session.setAttribute("loginInfo", user);
		//System.out.println(user.getNickname());
		return Result.of(103);
	}
	/**
	 * 更新头像 
	 */
	@Override
	public Result updateAvatar(int x,int y,int width,int height,String path ,HttpServletRequest request) {
		
		Date date=new Date();
		String a="/store/avatar/"
						  +new SimpleDateFormat("yyyy").format(date)+"/"
						  +new SimpleDateFormat("MM").format(date)+"/"
						  +new SimpleDateFormat("dd").format(date)+"/"
						  +UUID.randomUUID().toString()+".jpg";
		
		File local=new File(StoreRootPath,a);
		File dir = local.getParentFile();
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File file=new File(StoreRootPath,path);
		
		try {
			Thumbnails.of(file)
					  .sourceRegion(x, y, width, height)
					  .size(width, height)
					  .outputFormat("jpg")
					  .toFile(local);
//			System.out.println(local);
			User loginUser=LoginUtil.getLoginUser();
			if(loginUser==null) {
				return Result.of(1);//1表示未登录
			}
			
			User user = userDao.getOne(loginUser.getId());
			notifyDao.UpdateAvatarOnUser(a, user.getAvatar());
			user.setAvatar(a);
			userDao.save(user);
			request.getSession().setAttribute("loginInfo", user);
			System.out.println(user.getAvatar());
			return Result.of(2);//2表示修改成功
			
		} catch (IOException e) {
			e.printStackTrace();
			return Result.of(3,e.toString());//3表示异常
		}
	}
	
	/**
	 * 喜欢功能
	 */
	@Override
	@CacheEvict(value="favorCount",key="#p0")
	public Result saveFavor(long articleId) {
	
		User loginUser = LoginUtil.getLoginUser();
		if(loginUser==null) {
			return Result.of(0,"未登录");
		}
		
		Article article =articleDao.getOne(articleId);
		
		if(favorDao.find(loginUser.getId(), articleId)!=null) {
			return Result.of(1,"不能重复喜欢");
		}
		
		Favor favor = new Favor();
		favor.setArticle(article);
		favor.setCreated(new Date());
		favor.setUser(loginUser);
		favorDao.save(favor);
		
		//给文章作者发通知
		
		Notify notify = new Notify();
		notify.setAvatar(loginUser.getAvatar());
		notify.setCreated(new Date());
		notify.setTitle(loginUser.getNickname());
		notify.setUser(article.getUser());
		notify.setUrl("/ta/"+loginUser.getId());
		String s2 = String.format("喜欢了你的文章 - <a href = \"/view/%s.html\">%s</a>", article.getId(),article.getTitle());
		notify.setContent(s2);
		notify.setStaus(0);
		notifyDao.save(notify);
		
		return Result.of(2, "喜欢成功");
	}
	
	/**
	 * 关注功能
	 */
	@Override
	public Result saveFollow(long id) {

		User loginUser =LoginUtil.getLoginUser();
		if(loginUser==null) {
			return Result.of(0,"未登录");
		}
		if(followsDao.find(loginUser.getId(), id)!=null) {
			return Result.of(3,"已经关注，无需重复");
		}
		Follows follows = new Follows();
		follows.setCreated(new Date());
		follows.setSource(loginUser);
		User target = new User();
		target.setId(id);
		follows.setTarget(target);
		followsDao.save(follows);
		
		//给被关注的人发通知
		Notify notify = new Notify();
		notify.setAvatar(loginUser.getAvatar());
		notify.setCreated(new Date());
		notify.setTitle(loginUser.getNickname());
		notify.setUser(target);
		notify.setUrl("/ta/"+loginUser.getId());
		notify.setContent("关注了你，你的粉丝+1");
		notify.setStaus(0);
		notifyDao.save(notify);
		
		return Result.of(1,"关注成功");
	}
	//判断是否关注
	@Override
	public Result followCheck(Long id) {
	
		User loginUser = LoginUtil.getLoginUser();
		if(loginUser==null) {
			return Result.of(0,"未登录");
		}
		
		Follows follows = followsDao.find(loginUser.getId(), id);
		if(follows==null) {
			return Result.of(1,"未关注");
		}
		return Result.of(2,"已关注");
	}
	//取消关注
	@Override
	@Transactional//增加事务
	public Result unFollow(long id) {
		
		User loginUser = LoginUtil.getLoginUser();
		if(loginUser==null) {
			return Result.of(0, "未登录");
		}
		
		Follows follows = followsDao.find(loginUser.getId(), id);
		followsDao.delete(follows);
		
		return Result.of(1, "取消关注成功");
	}
	//取消喜欢
	@Override
	public Result unfavor(long id) {
		User loginUser = LoginUtil.getLoginUser();
		if(loginUser==null) {
			return Result.of(0, "未登录");
		}
		
		Favor favor = favorDao.find(loginUser.getId(), id);
		favorDao.delete(favor);
		
		return Result.of(1, "取消喜欢成功");
	}
	//修改邮箱功能
	@Override
	public Result editEmail(String newEmail) {
		User loginUser = LoginUtil.getLoginUser(); 
		if(loginUser.getEmail() == newEmail) {
			return Result.of(100, "请输入新邮箱");
		}
		if(!EmailUtil.isEmail(newEmail)) {
			return Result.of(300, "请输入正规邮箱");
		}
		loginUser.setEmail(newEmail);
		userDao.save(loginUser);
		return Result.of(200, "修改成功");
	}
	@Override
	public Result editPwd(String email, String password) {
		
		if(EmailUtil.isEmail(email)) {
			User user = userDao.findByEmail(email);
			if(user == null) {
				return Result.of(400, "该邮箱没有绑定账号,无需修改");
			}
			user.setSalt(UUID.randomUUID().toString());//改个新的盐值
			String m1 =password;
			String s1 =user.getSalt();
			String r1 =md5(sha512(m1)+md5(s1)+sha512(m1+s1));
			user.setPassword(r1);
			userDao.save(user);
			boolean flag = SendEmail.SendPassword(email, password);
			if(flag) {
				return Result.of(200, "修改成功,并已发送邮件");
			}
			return Result.of(300, "修改成功,但邮件发送失败");
		}	
		return Result.of(100, "请输入正规邮箱");
	}
}
