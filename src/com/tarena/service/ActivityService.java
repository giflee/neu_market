package com.tarena.service;

import java.util.List;

import com.tarena.entity.Activity;
import com.tarena.entity.Problem;

public interface ActivityService {

	Activity addActivity(String activity_title ,String activity_body);	
	void deleteActivity(String activity_id );
	List<Activity> findAll();
	void update(Activity activity);
	Activity findById(String activity_id);
}
