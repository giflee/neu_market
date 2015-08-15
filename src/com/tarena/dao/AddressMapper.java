package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Address;
import com.tarena.entity.User;

public interface AddressMapper {
	public List<Address> adminFindByUserId(String userId);
}
