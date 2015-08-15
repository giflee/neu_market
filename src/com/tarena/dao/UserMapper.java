package com.tarena.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tarena.entity.Goods;
import com.tarena.entity.PageInfo;
import com.tarena.entity.User;

public interface UserMapper {
	
	List<User> adminFindAllUser();
	List<User> adminFindByUserName(String userName);
	void adminUpdateUserPwd(String userId,String PWD);
	/*分页查询*/
	List<User> findUserListPage(@Param("page") PageInfo page);//模糊匹配  分页查询
	
	//前台
	User findByName(String user_name);
	void register(User user);
}
