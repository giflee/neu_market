package com.tarena.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Goods implements Serializable{
	
	private String goods_id;
	private Type type;
	private String goods_name;
	private String goods_pic;
	private Double goods_price;
	private Timestamp goods_creatime;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_pic() {
		return goods_pic;
	}
	public void setGoods_pic(String goods_pic) {
		this.goods_pic = goods_pic;
	}
	public Double getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Double goods_price) {
		this.goods_price = goods_price;
	}
	public Timestamp getGoods_creatime() {
		return goods_creatime;
	}
	public void setGoods_creatime(Timestamp goods_creatime) {
		this.goods_creatime = goods_creatime;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Goods [goods_id=" + goods_id + ", type=" + type
				+ ", goods_name=" + goods_name + ", goods_pic=" + goods_pic
				+ ", goods_price=" + goods_price + ", goods_creatime="
				+ goods_creatime + "]";
	}
	
}
