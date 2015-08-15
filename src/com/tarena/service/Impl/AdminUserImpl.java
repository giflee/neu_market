package com.tarena.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.AddressMapper;
import com.tarena.dao.UserMapper;
import com.tarena.entity.Address;
import com.tarena.entity.Admin;
import com.tarena.entity.PageInfo;
import com.tarena.entity.User;
import com.tarena.service.AdminUserService;
import com.tarena.util.Md5Util;

@Service
public class AdminUserImpl implements AdminUserService {

	@Resource
	private UserMapper userMapper;
	@Resource
	private AddressMapper adressMapper;
	@Override
	public List<User> adminFindAllUser(PageInfo page) {
		List<User> userList = userMapper.findUserListPage(page);
		return userList;
	}
	@Override
	public List<Address> adminFindByUserId(String userId) {
		List<Address> addressList = new ArrayList<Address>();
		addressList = adressMapper.adminFindByUserId(userId);
		return addressList;
	}
	@Override
	public List<User> adminFindByUserName(String userName) {
		List<User> userList = userMapper.adminFindByUserName(userName);
		return userList;
	}
	@Override
	public void adminUpdateUserPwd(String userId,String userPwd) {
		
		userMapper.adminUpdateUserPwd(userId,userPwd);
	}
}
