package com.tarena.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.JsonWrapper;
import com.tarena.entity.Type;
import com.tarena.service.TypeService;

@Controller
@RequestMapping("/type")
public class AdminTypeController {
	
	@Autowired
	private TypeService typeService;
	
	@RequestMapping("/show.do")
	public @ResponseBody JSONObject show(){
		List<Type> typeList = typeService.getAllType();
		List<Object> list = new ArrayList<Object>();
		for(Type type:typeList){
			JSONObject jo = new JSONObject();
			jo.put("type_id", type.getType_id());
			jo.put("type_name", type.getType_name());
			jo.put("goods_num", typeService.getTypeNum(type.getType_id()));
			list.add(jo);
		}
		JSONObject content = new JSONObject();
		content.put("typeList", list);
		System.out.println(typeList);
		return new JsonWrapper.Builder(true, null, null, content).build();
	}

	@RequestMapping("/add.do")
	public @ResponseBody JSONObject add(String type_name){
		typeService.addType(type_name);
		return new JsonWrapper.Builder(true, null, "添加成功", null).build();
	}
	
	@RequestMapping("/find.do")
	public @ResponseBody JSONObject find(String type_name){
		List<Type> typeList = typeService.findTypes(type_name);
		List<Object> list = new ArrayList<Object>();
		for(Type type:typeList){
			JSONObject jo = new JSONObject();
			jo.put("type_id", type.getType_id());
			jo.put("type_name", type.getType_name());
			jo.put("goods_num", typeService.getTypeNum(type.getType_id()));
			list.add(jo);
		}
		JSONObject content = new JSONObject();
		content.put("typeList", list);
		System.out.println(typeList);
		return new JsonWrapper.Builder(true, null, "查找成功", content).build();
	}
	
	@RequestMapping("/delete.do")
	public @ResponseBody JSONObject delete(String type_id){
		System.out.println("删除ID"+type_id);
		typeService.deleteType(type_id);
		return new JsonWrapper.Builder(true, null, "删除成功", null).build();
	}
}
