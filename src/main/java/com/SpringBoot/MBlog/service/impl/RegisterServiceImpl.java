package com.SpringBoot.MBlog.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.SpringBoot.MBlog.dao.UserDao;
import com.SpringBoot.MBlog.dao.User_PowerDao;
import com.SpringBoot.MBlog.entity.User;
import com.SpringBoot.MBlog.entity.User_Power;
import com.SpringBoot.MBlog.service.RegisterService;
import com.SpringBoot.MBlog.util.DigestHelper;
import com.SpringBoot.MBlog.util.EmailUtil;
import com.SpringBoot.MBlog.vo.Result;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired UserDao _userdao;
	@Autowired User_PowerDao powerDao;
	/**
	 * 注册功能
	 */
	@Override
	public Map<String, Object> register(User user) {
		
		if(_userdao.findByUsername(user.getUsername())!=null) {
			return Result.of(100,"用户名已经存在");
		}
		if(_userdao.findByEmail(user.getEmail())!=null) {
			return Result.of(101,"邮箱已经存在，请更换");
		}
		if(_userdao.findByNickname(user.getNickname())!=null) {
			return Result.of(102,"昵称已经存在，请更换");
		}
		user.setAvatar("/store/avatar/default.jpg");
		user.setCreateTime(new Date());
		user.setLast_login_time(new Date());
		user.setState(1);//设置状态为正常用户
		//对明文密码加密
		user.setSalt(UUID.randomUUID().toString());
		String m =user.getPassword();
		String s =user.getSalt();
		String r =md5(sha512(m)+md5(s)+sha512(m+s));
		user.setPassword(r);
		_userdao.save(user);

		//再把user查处来设置权限设为初始权限普通用户在保存
		User_Power power =new User_Power();
		User oldUser = _userdao.findById(user.getId());
		power.setRole(0);
		power.setUser(oldUser);
		oldUser.setPower(power);
		
		powerDao.save(power);
		_userdao.save(oldUser);
		
		return Result.of(200).put("msg", "成功");		
	}
	protected String basepath() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String path = request.getContextPath();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	}
	private String md5(String text) {
		return DigestHelper.md5(text);
	}
	private String sha512(String text) {
		return DigestHelper.sha512(text);
	}
	/**
	 * 登录功能
	 */
	@Override
	public Result login(String username, String password) {
		User user =null;
		if(EmailUtil.isEmail(username)) {
			user = _userdao.findByEmail(username);
		}
		else{
			user = _userdao.findByUsername(username);
		}
		if(user==null) {
			return Result.of(100,"账户不存在，请注册");
		}
		if(user.getState()==2) {
			return Result.of(103, "该账户因违规已被禁用，请联系管理员");
		}
		String m = password;      //明文密码
		String s = user.getSalt();//加密盐值
		String r = md5(sha512(m)+md5(s)+sha512(m+s));
		
		if(r.equals(user.getPassword())) {
			
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session =  request.getSession();
			session.setAttribute("loginInfo", user);
			//System.out.println(LoginUtil.getLoginUser());
			_userdao.updateLoginDate(new Date(), user.getId());
			return Result.of(200,"登录成功");
		}
		return Result.of(100,"密码不正确");
	}

}
