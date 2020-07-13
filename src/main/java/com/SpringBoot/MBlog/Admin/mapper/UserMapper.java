package com.SpringBoot.MBlog.Admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.SpringBoot.MBlog.entity.User;

public interface UserMapper {
	
	/**
	 * 查询热门用户
	 * @param end 查询条数
	 * @return
	 */
	List<User> ApiFindUserAll(int end);
	/**
	 * 查询所有用户列表，可通过对应参数实现综合查询
	 * @param nickname
	 * @param beginDateTime
	 * @param endDateTime
	 * @param state
	 * @param role
	 * @return
	 */
	List<Map<String, Object>> findUserVos(@Param("nickname")String nickname,@Param("beginDateTime")String beginDateTime,
											@Param("endDateTime") String endDateTime,@Param("state") Integer state,@Param("role") Integer role);
	//通过id查询用户
	User findUserById(Long id);

	//修改用户
	Integer editUser(@Param("userId") Long userId,@Param("username") String username,@Param("email") String email,
						@Param("state") Integer state,@Param("role") Integer role);

	//删除用户
	void delUser(Long id);
	//删除某用户有关动态
	void delNotifyByUser(Long u_id);
	/**
	 * 查询用户
	 * @param nickname
	 * @return
	 */
	User findUserByNickname(String nickname);
	/**
	 * 通过用户名查询用户
	 * @param username
	 * @return
	 */
	User findUserByUsername(String username);
}
