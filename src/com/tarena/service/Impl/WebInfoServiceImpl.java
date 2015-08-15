package com.tarena.service.Impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.tarena.dao.WebInfoMapper;
import com.tarena.entity.WebInfo;
import com.tarena.service.WebInfoService;

@Service
public class WebInfoServiceImpl implements WebInfoService, Serializable {

	@Resource
	private  WebInfoMapper webInfoMapper;
	@Override
	public List<WebInfo> findAll() {
		List<WebInfo> webinfolist = new ArrayList<WebInfo>();
		webinfolist = webInfoMapper.webInfoFindAll();
		return webinfolist;
	}

	@Override
	public void update(WebInfo webInfo) {
		webInfoMapper.webInfoUpdate(webInfo);
	}

	@Override
	public void delete(String web_id) {
		webInfoMapper.webInfoDelete(web_id);
	}

	@Override
	public void add(WebInfo webInfo) {
		webInfoMapper.webInfoAdd(webInfo);
	}

	@Override
	public WebInfo findById(String web_id) {
		WebInfo webInfo = new WebInfo();
		webInfo = webInfoMapper.webInfoFindById(web_id);
		return webInfo;
	}

	@Override
	public List<WebInfo> singleFindAll() {
		List<WebInfo> singlelist = new ArrayList<WebInfo>();
		singlelist = webInfoMapper.singleFindAll();
		return singlelist;
	}

	@Override
	public void singleAdd(WebInfo webInfo) {
		webInfoMapper.singleInsert(webInfo);
	}

	@Override
	public void singleUpdate(WebInfo webInfo) {
		webInfoMapper.singleUpdate(webInfo);
	}

}
