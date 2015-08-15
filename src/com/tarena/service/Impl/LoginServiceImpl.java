package com.tarena.service.Impl;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarena.dao.UserMapper;
import com.tarena.entity.User;
import com.tarena.redis.RedisTools;
import com.tarena.service.LoginService;
import com.tarena.util.Md5Util;
@Service
public class LoginServiceImpl implements LoginService,Serializable{

	@Autowired
	private UserMapper userMapper;
	@Override
	public User login(String name, String password) {
		try {
			String rpwd = RedisTools.getPassword(name);
			if(rpwd!=null){
				String id = RedisTools.getId(name);
				User user = new User();
				user.setUser_name(name);
				user.setUser_id(id);
				user.setUser_password(rpwd);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return  userMapper.findByName(name);
		
	}
	@Override
	public void register(String name, String pwd, String email) {
		User user = new User();
		user.setUser_id(UUID.randomUUID().toString());
		user.setUser_name(name);
		user.setUser_password(Md5Util.md5(pwd));
		user.setUser_email(email);
		userMapper.register(user);
	}
	
	
}
