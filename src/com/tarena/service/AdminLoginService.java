package com.tarena.service;

import com.tarena.entity.Admin;

public interface AdminLoginService {
	public Admin findByAdminName(String adminName);
}
