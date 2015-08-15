package com.tarena.service;

import org.springframework.stereotype.Service;

import com.tarena.entity.User;

public interface LoginService {

	User login(String name,String password);
	void register(String name,String pwd,String email);
}
