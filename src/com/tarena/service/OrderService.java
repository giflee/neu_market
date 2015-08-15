package com.tarena.service;

import java.util.List;

import com.tarena.entity.Order;
import com.tarena.entity.OrderGoods;
import com.tarena.entity.PageInfo;

public interface OrderService {

	List<Order> findOrders(PageInfo page,String order_status,String address_phone);
	List<OrderGoods> findOrderGoodsById(String order_id);
	
	//前台
	List<Order> findOrdersPre(String user_id);
	void save(Order order);
	Order findOrderById(String id);
	void update(Order order);
	
	void saveUO(String id2,String id3);
	
	List<Order> findByPhone(String phone);
}
