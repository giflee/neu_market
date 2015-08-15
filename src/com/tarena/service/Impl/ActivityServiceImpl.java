package com.tarena.service.Impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.ActivityMapper;
import com.tarena.entity.Activity;
import com.tarena.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Resource
	private ActivityMapper activityMapper;
	
	@Override
	public Activity addActivity(String activity_title, String activity_body) {

		Activity activity = new Activity();
		activity.setActivity_id(UUID.randomUUID().toString());
		activity.setActivity_title(activity_title);
		activity.setActivity_body(activity_body);
		activity.setActivity_time(new Timestamp(System.currentTimeMillis()));
		activityMapper.addActivity(activity);
		return activity;
	}

	@Override
	public void deleteActivity(String activity_id) {

		activityMapper.deleteActivity(activity_id);
	}

	@Override
	public List<Activity> findAll() {
		return activityMapper.find();
	}

	@Override
	public void update(Activity activity) {
		activityMapper.update(activity);
	}

	@Override
	public Activity findById(String activity_id) {
		return activityMapper.findById(activity_id);
	}

}
