package com.tarena.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.Address;
import com.tarena.entity.JsonWrapper;
import com.tarena.entity.PageInfo;
import com.tarena.entity.User;
import com.tarena.service.AdminUserService;
import com.tarena.util.Md5Util;

@Controller
@RequestMapping("/admin_manage")
public class AdminUserController implements Serializable {

	@Resource
	private AdminUserService adminUserService;
	
	@RequestMapping("/user.do")
	@ResponseBody
	public JSONObject findAll(String page1){
		int currentPage = page1==null?1:Integer.parseInt(page1);
		int pageSize = 2;
		if (currentPage<=0){
			currentPage =1;
		}
		int currentResult = (currentPage-1) * pageSize;
		
		PageInfo page = new PageInfo();
		page.setShowCount(pageSize);
		page.setCurrentResult(currentResult);
		
		List<User> userList = adminUserService.adminFindAllUser(page);
		int totalCount = page.getTotalResult();
		JSONObject content = new JSONObject();
		content.put("total", totalCount);
		content.put("size", pageSize);
		content.put("userList", userList);
		return new JsonWrapper.Builder(true, "", "", content).build();
	}
	
	@RequestMapping("/findByUserId.do")
	@ResponseBody
	public JSONObject findByUserId(String userId){
		JSONObject json = new JSONObject();
		List<Address> addressList = new ArrayList<Address>();
		addressList = adminUserService.adminFindByUserId(userId);
		json.put("addressList", addressList);
		return new JsonWrapper.Builder(true, "", "", json).build();
	}
	
	@RequestMapping("/findByUserName.do")
	@ResponseBody
	public JSONObject findByUserName(String userName){
		JSONObject json = new JSONObject();
		List<User> userList = new ArrayList<User>();
		userList = adminUserService.adminFindByUserName(userName);
		json.put("userList", userList);
		return new JsonWrapper.Builder(true, "", "", json).build();
	}
	@RequestMapping("/reset.do")
	@ResponseBody
	public JSONObject resetUserPwd(String userId){
		JSONObject json = new JSONObject();
		String userPWD = Md5Util.md5("123456");
		adminUserService.adminUpdateUserPwd(userId,userPWD);
		return new JsonWrapper.Builder(true, "", "", json).build();
	}
}
