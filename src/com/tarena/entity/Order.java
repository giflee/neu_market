package com.tarena.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable{
	
	
	private String order_id;
	private String address;
	private Integer order_status;
	private String pay_way;
	private Timestamp order_time;
	private Timestamp receive_time;
	private String address_person;
	private String address_phone;
	private double order_price;
	private String order_mark;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	public String getPay_way() {
		return pay_way;
	}
	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	public Timestamp getReceive_time() {
		return receive_time;
	}
	public void setReceive_time(Timestamp receive_time) {
		this.receive_time = receive_time;
	}
	public String getAddress_person() {
		return address_person;
	}
	public void setAddress_person(String address_person) {
		this.address_person = address_person;
	}
	public String getAddress_phone() {
		return address_phone;
	}
	public void setAddress_phone(String address_phone) {
		this.address_phone = address_phone;
	}
	public double getOrder_price() {
		return order_price;
	}
	public void setOrder_price(double order_price) {
		this.order_price = order_price;
	}
	public String getOrder_mark() {
		return order_mark;
	}
	public void setOrder_mark(String order_mark) {
		this.order_mark = order_mark;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", address=" + address
				+ ", order_status=" + order_status + ", pay_way=" + pay_way
				+ ", order_time=" + order_time + ", receive_time="
				+ receive_time + ", address_person=" + address_person
				+ ", address_phone=" + address_phone + ", order_price="
				+ order_price + ", order_mark=" + order_mark + "]";
	}
	
	
	
	
}
