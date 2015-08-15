package com.tarena.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.JsonWrapper;
import com.tarena.entity.Order;
import com.tarena.service.OrderService;

@Controller
@RequestMapping("/stage/order")
public class StageOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/find_by_phone.do")
	@ResponseBody
	public JSONObject findByPhone(String phone){
		
		List<Order> list = orderService.findByPhone(phone);
		System.out.println("-------"+list);
		JSONObject jo = new JSONObject();
		jo.put("orderList", list);
		return new JsonWrapper.Builder(true, null, "查找成功", jo).build();
	}
	
	@RequestMapping("/receive.do")
	@ResponseBody
	public  JSONObject receive(String id){
		Order order = orderService.findOrderById(id);
		order.setOrder_status(0);
		orderService.update(order);
		return new JsonWrapper.Builder(true, null, "确认收货成功", null).build();
	}
}
