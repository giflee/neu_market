package com.tarena.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Activity implements Serializable{
	
	private String activity_id;
	private String activity_title;
	private String activity_body;
	private Timestamp activity_time;
	public String getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}
	public String getActivity_title() {
		return activity_title;
	}
	public void setActivity_title(String activity_title) {
		this.activity_title = activity_title;
	}
	public String getActivity_body() {
		return activity_body;
	}
	public void setActivity_body(String activity_body) {
		this.activity_body = activity_body;
	}
	public Timestamp getActivity_time() {
		return activity_time;
	}
	public void setActivity_time(Timestamp activity_time) {
		this.activity_time = activity_time;
	}
	@Override
	public String toString() {
		return "Activity [activity_id=" + activity_id + ", activity_title="
				+ activity_title + ", activity_body=" + activity_body
				+ ", activity_time=" + activity_time + "]";
	}
	
}
