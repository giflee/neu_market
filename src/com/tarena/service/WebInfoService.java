package com.tarena.service;

import java.util.List;

import com.tarena.entity.WebInfo;

public interface WebInfoService {

	List<WebInfo> findAll();
	void update(WebInfo webInfo);
	void delete(String web_id);
	void add(WebInfo webInfo);
	WebInfo findById(String web_id);
	
	List<WebInfo> singleFindAll();
	void singleAdd(WebInfo webInfo);
	void singleUpdate(WebInfo webInfo);
}
