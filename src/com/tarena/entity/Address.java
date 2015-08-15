package com.tarena.entity;

import java.io.Serializable;

public class Address implements Serializable{

	private String address_id;
	private String user_id;
	private String address_name;
	private String address_person;
	private String address_phone;
	public String getAddress_id() {
		return address_id;
	}
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAddress_name() {
		return address_name;
	}
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
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
	@Override
	public String toString() {
		return "Address [address_id=" + address_id + ", user_id=" + user_id
				+ ", address_name=" + address_name + ", address_person="
				+ address_person + ", address_phone=" + address_phone + "]";
	}
	
}
