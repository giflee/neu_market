package com.tarena.entity;

import java.io.Serializable;

public class PayWay implements Serializable{
	
	private String pay_way_id;
	private String pay_way_name;
	public String getPay_way_id() {
		return pay_way_id;
	}
	public void setPay_way_id(String pay_way_id) {
		this.pay_way_id = pay_way_id;
	}
	public String getPay_way_name() {
		return pay_way_name;
	}
	public void setPay_way_name(String pay_way_name) {
		this.pay_way_name = pay_way_name;
	}
	@Override
	public String toString() {
		return "PayWay [pay_way_id=" + pay_way_id + ", pay_way_name="
				+ pay_way_name + "]";
	}

}
