package com.tarena.dao;

import java.util.List;

import com.tarena.entity.WebInfo;

public interface WebInfoMapper {
	List<WebInfo> webInfoFindAll();
	void webInfoUpdate(WebInfo webinfo);
	void webInfoDelete(String web_id);
	void webInfoAdd(WebInfo webinfo);
	WebInfo webInfoFindById(String web_id);
	
	List<WebInfo> singleFindAll();
	void singleInsert(WebInfo webInfo);
	void singleUpdate(WebInfo webInfo);
}
