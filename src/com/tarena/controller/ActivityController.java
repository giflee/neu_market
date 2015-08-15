package com.tarena.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tarena.entity.Activity;
import com.tarena.entity.Goods;
import com.tarena.entity.JsonWrapper;
import com.tarena.entity.PageInfo;
import com.tarena.entity.Problem;
import com.tarena.service.ActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@RequestMapping("/addActivity.do")
	public @ResponseBody JSONObject addActivity(String activity_title ,String activity_body ) throws Exception{
		activityService.addActivity( activity_title, activity_body);
		return new JsonWrapper.Builder(true,null, "添加成功", null).build();
	}
	
	@RequestMapping("/find.do")
	public @ResponseBody JSONObject find(){
		JSONObject content = new JSONObject();
		List<Activity> activityList = activityService.findAll();
		content.put("activityList", activityList);
		return new JsonWrapper.Builder(true, null, null, content).build();
	}
	
	@RequestMapping("/delete.do")
	public @ResponseBody JSONObject delete(String activity_id){
		activityService.deleteActivity(activity_id);
		return new JsonWrapper.Builder(true, null, "删除成功", null).build();
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public JSONObject update(String activity_id,String activity_title,String activity_body){
		Activity activity = activityService.findById(activity_id);
		activity.setActivity_title(activity_title);
		activity.setActivity_body(activity_body);
		activityService.update(activity);
		return new JsonWrapper.Builder(true, null, "修改成功", null).build();
	}
}
