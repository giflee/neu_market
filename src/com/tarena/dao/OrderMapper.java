package com.tarena.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tarena.entity.Order;
import com.tarena.entity.OrderGoods;
import com.tarena.entity.PageInfo;

public interface OrderMapper {

	List<Order> findOrderListPage(@Param("page") PageInfo page,@Param(value="order_status")String order_status,
			@Param(value="address_phone")String address_phone);//分页查询 查找
	
	List<OrderGoods> findById(String order_id);
	
	//前台
	List<Order> findOrderPre(@Param(value="user_id")String user_id);
	void save(Order order);
	Order findOrderById(String order_id);
	void update(Order order);
	
	void saveUO(@Param(value="order_user_id")String order_user_id,@Param(value="order_id")String order_id,
			@Param(value="user_id")String user_id);	
	
	List<Order> findByPhone(String phone);
	
}
