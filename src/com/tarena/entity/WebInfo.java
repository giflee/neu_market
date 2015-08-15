package com.tarena.entity;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class WebInfo implements Serializable {
	private String web_id;
	private String web_title;
	private String web_words;
	private String web_nav1;
	private String web_nav2;
	private String web_nav3;
	private String web_nav4;
	private String web_footer;
	private String web_maker;
	
	
	
	public String getWeb_id() {
		return web_id;
	}
	public void setWeb_id(String web_id) {
		this.web_id = web_id;
	}
	public String getWeb_title() {
		return web_title;
	}
	public void setWeb_title(String web_title) {
		this.web_title = web_title;
	}
	public String getWeb_words() {
		return web_words;
	}
	public void setWeb_words(String web_words) {
		this.web_words = web_words;
	}
	public String getWeb_nav1() {
		return web_nav1;
	}
	public void setWeb_nav1(String web_nav1) {
		this.web_nav1 = web_nav1;
	}
	public String getWeb_nav2() {
		return web_nav2;
	}
	public void setWeb_nav2(String web_nav2) {
		this.web_nav2 = web_nav2;
	}
	public String getWeb_nav3() {
		return web_nav3;
	}
	public void setWeb_nav3(String web_nav3) {
		this.web_nav3 = web_nav3;
	}
	public String getWeb_nav4() {
		return web_nav4;
	}
	public void setWeb_nav4(String web_nav4) {
		this.web_nav4 = web_nav4;
	}
	public String getWeb_footer() {
		return web_footer;
	}
	public void setWeb_footer(String web_footer) {
		this.web_footer = web_footer;
	}
	public String getWeb_maker() {
		return web_maker;
	}
	public void setWeb_maker(String web_maker) {
		this.web_maker = web_maker;
	}
	@Override
	public String toString() {
		return "WebInfo [web_title=" + web_title + ", web_words=" + web_words
				+ ", web_nav1=" + web_nav1 + ", web_nav2=" + web_nav2
				+ ", web_nav3=" + web_nav3 + ", web_nav4=" + web_nav4
				+ ", web_footer=" + web_footer + ", web_maker=" + web_maker
				+ "]";
	}
	
	
}
