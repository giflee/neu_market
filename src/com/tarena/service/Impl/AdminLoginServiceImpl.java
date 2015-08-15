package com.tarena.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.tarena.dao.AdminMapper;
import com.tarena.entity.Admin;
import com.tarena.service.AdminLoginService;
@Service
public class AdminLoginServiceImpl implements AdminLoginService {
	
	@Resource
	private AdminMapper adminMapper;
	@Override
	public Admin findByAdminName(String adminName) {
		Admin admin = new Admin();
		admin = adminMapper.findByAdminName(adminName);
		return admin;
	}

}
