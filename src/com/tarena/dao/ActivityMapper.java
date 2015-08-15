package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Activity;
import com.tarena.entity.Problem;
import com.tarena.entity.Type;

public interface ActivityMapper {

	//查询所有活动
	List<Activity> find();
	//新增活动
	void addActivity(Activity activity);
	//删除活动
	void deleteActivity(String activity_id);
	//修改活动
	void update(Activity activity);
	
	Activity findById(String activity_id);
}

















