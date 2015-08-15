package com.tarena.service;

import java.util.List;

import com.tarena.entity.Address;
import com.tarena.entity.Admin;
import com.tarena.entity.PageInfo;
import com.tarena.entity.User;

public interface AdminUserService {
	List<User> adminFindAllUser(PageInfo page);
	List<Address> adminFindByUserId(String userId);
	List<User> adminFindByUserName(String userName);
	void adminUpdateUserPwd(String userId,String userPwd);
}
