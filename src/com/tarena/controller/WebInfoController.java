package com.tarena.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.JsonWrapper;
import com.tarena.entity.WebInfo;
import com.tarena.service.WebInfoService;

@Controller
@RequestMapping("/webinfo")
public class WebInfoController implements Serializable {
	
	private String golbal_id;
	@Resource
	private WebInfoService webInfoService;
	
	@RequestMapping("/find.do")
	@ResponseBody
	public  JSONObject findAll(){
		JSONObject json = new JSONObject();
		List<WebInfo> webinfolist = new ArrayList<WebInfo>();
		webinfolist = webInfoService.findAll();
		json.put("webinfolist", webinfolist);
		return new JsonWrapper.Builder(true, "", "", json).build();
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public  JSONObject update(WebInfo webInfo){
		JSONObject json = new JSONObject();
		webInfoService.update(webInfo);
		return new JsonWrapper.Builder(true, "", "success", json).build();
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public  JSONObject delete(String web_id){
		JSONObject json = new JSONObject();
		webInfoService.delete(web_id);
		return new JsonWrapper.Builder(true, "", "success", json).build();
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public  JSONObject add(WebInfo webInfo){
		JSONObject json = new JSONObject();
		webInfo.setWeb_id(UUID.randomUUID().toString());
		webInfoService.add(webInfo);
		return new JsonWrapper.Builder(true, "", "success", json).build();
	}
	
	@RequestMapping("/findById.do")
	@ResponseBody
	public  JSONObject findById(){
		JSONObject json = new JSONObject();
		String web_id = getGolbal_id();
		System.out.println(web_id);
		if(web_id == null){
			return new JsonWrapper.Builder(false, "", "success", json).build();
		}
		WebInfo webInfo = new WebInfo();
		webInfo = webInfoService.findById(web_id);
		json.put("webInfo", webInfo);
		return new JsonWrapper.Builder(true, "", "success", json).build();
	}
	
	
	@RequestMapping("/show.do")
	@ResponseBody
	public  JSONObject show(String info_id){
		JSONObject json = new JSONObject();
		setGolbal_id(info_id);
		return new JsonWrapper.Builder(true, "", "success", json).build();
	}
	
	
	@RequestMapping("/singleFindAll.do")
	@ResponseBody
	public  JSONObject singleFindAll(){
		JSONObject json = new JSONObject();
		List<WebInfo> webinfolist = new ArrayList<WebInfo>();
		webinfolist = webInfoService.singleFindAll();
		json.put("webinfolist", webinfolist);
		return new JsonWrapper.Builder(true, "", "success", json).build();
	}
	
	
	@RequestMapping("/singleInsert.do")
	@ResponseBody
	public  JSONObject singleInsert(WebInfo webInfo){
		JSONObject json = new JSONObject();
		webInfoService.singleAdd(webInfo);
		return new JsonWrapper.Builder(true, "", "success", json).build();
	}
	
	@RequestMapping("/singleUpdate.do")
	@ResponseBody
	public  JSONObject singleUpdate(WebInfo webInfo){
		JSONObject json = new JSONObject();
		webInfoService.singleUpdate(webInfo);
		return new JsonWrapper.Builder(true, "", "success", json).build();
	}

	public String getGolbal_id() {
		return golbal_id;
	}

	public void setGolbal_id(String golbal_id) {
		this.golbal_id = golbal_id;
	}
}
