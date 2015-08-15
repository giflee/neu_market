package com.tarena.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.JsonWrapper;
import com.tarena.entity.Order;
import com.tarena.entity.OrderGoods;
import com.tarena.entity.PageInfo;
import com.tarena.service.OrderService;

@Controller
@RequestMapping("/order")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/find.do")
	public @ResponseBody JSONObject find(String page1,String order_status,String address_phone){
		int currentPage = page1==null?1:Integer.parseInt(page1);
		int pageSize = 2;
		if (currentPage<=0){
			currentPage =1;
		}
		int currentResult = (currentPage-1) * pageSize;
		System.out.println("订单状态"+order_status);
		PageInfo page = new PageInfo();
		page.setShowCount(pageSize);
		page.setCurrentResult(currentResult);
		List<Order> orderList = orderService.findOrders(page, order_status, address_phone);
		int totalCount = page.getTotalResult();
		
		System.out.println(orderList);
		JSONObject content = new JSONObject();
		content.put("orderList",orderList );
		content.put("total", totalCount);
		content.put("size", pageSize);
		
		return new JsonWrapper.Builder(true, null, "查找成功", content).build();
	}
	
	@RequestMapping("/find/goods.do")
	public @ResponseBody JSONObject findGoods(String order_id){
		List<OrderGoods> list =  orderService.findOrderGoodsById(order_id);
		System.out.println(list);
		JSONObject jo = new JSONObject();
		jo.put("orderGoodsList", list);
		return new JsonWrapper.Builder(true, null, "查找成功", jo).build();
	}
}
