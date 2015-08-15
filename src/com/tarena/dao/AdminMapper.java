package com.tarena.dao;

import com.tarena.entity.Admin;
import com.tarena.entity.User;

public interface AdminMapper {
	public Admin findByAdminName(String adminName);
}
