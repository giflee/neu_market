package com.tarena.controller;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.Admin;
import com.tarena.entity.JsonWrapper;
import com.tarena.service.AdminLoginService;

@RequestMapping("/admin")
@Controller
public class AdminLoginController implements Serializable {

	@Resource
	private AdminLoginService adminLoginService;
	@RequestMapping("/login.do")
	@ResponseBody
	public  JSONObject adminLogin(String adminName,String adminPwd,HttpSession session){
		JSONObject json = new JSONObject();
		if(adminName.equals("")){
			//用户名为空
			return new JsonWrapper.Builder(false, "", "用户名不能为空。", json).build();
		}
		if(adminPwd.equals("")){
			return new JsonWrapper.Builder(false, "", "密码不能为空。", json).build();
		}
		Admin admin = adminLoginService.findByAdminName(adminName);
		if(admin == null){
			//用户名不存在
			return new JsonWrapper.Builder(false, "", "用户名不存在。", json).build();
		}else{
			if(!(adminPwd.equals(admin.getAdmin_password()))){
				//密码错误
				return new JsonWrapper.Builder(false, "", "密码错误。", json).build();
			}else{
				session.setAttribute("admin", admin.getAdmin_name());
				return new JsonWrapper.Builder(true, "", "", json).build();
			}
		}
	}
	
	@RequestMapping("/adminLogout.do")
	@ResponseBody
	public JSONObject adminLoginOut(HttpSession session){
		System.out.println("session清空了");
		session.invalidate();
		return null;
	}
	
	
	
}
