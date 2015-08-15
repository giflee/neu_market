package com.tarena.entity;

import java.io.Serializable;

public class OrderGoods implements Serializable{
	
	private String order_goods_id;
	private Order order;
	private String goods_name;
	private double good_price;
	private int order_goods_num;
	public String getOrder_goods_id() {
		return order_goods_id;
	}
	public void setOrder_goods_id(String order_goods_id) {
		this.order_goods_id = order_goods_id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public double getGood_price() {
		return good_price;
	}
	public void setGood_price(double good_price) {
		this.good_price = good_price;
	}
	public int getOrder_goods_num() {
		return order_goods_num;
	}
	public void setOrder_goods_num(int order_goods_num) {
		this.order_goods_num = order_goods_num;
	}
	@Override
	public String toString() {
		return "OrderGoods [order_goods_id=" + order_goods_id + ", order="
				+ order + ", goods_name=" + goods_name + ", good_price="
				+ good_price + ", order_goods_num=" + order_goods_num + "]";
	}
	
}
