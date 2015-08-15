package com.tarena.service.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarena.dao.OrderMapper;
import com.tarena.entity.Order;
import com.tarena.entity.OrderGoods;
import com.tarena.entity.PageInfo;
import com.tarena.service.OrderService;
@Service
public class OrderServiceImpl  implements OrderService{
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public List<Order> findOrders(PageInfo page, String order_status,
			String address_phone) {
		return orderMapper.findOrderListPage(page, order_status, address_phone);
	}

	@Override
	public List<OrderGoods> findOrderGoodsById(String order_id) {
		return orderMapper.findById(order_id);
	}

	@Override
	public List<Order> findOrdersPre(String user_id) {
		return orderMapper.findOrderPre(user_id);
	}
	

	@Override
	public void save(Order order) {
		orderMapper.save(order);
	}

	@Override
	public Order findOrderById(String id) {
		return orderMapper.findOrderById(id);
	}

	@Override
	public void update(Order order) {
		orderMapper.update(order);
	}

	@Override
	public void saveUO( String id2, String id3) {
		orderMapper.saveUO(UUID.randomUUID().toString(), id2, id3);
	}

	@Override
	public List<Order> findByPhone(String phone) {
		return orderMapper.findByPhone(phone);
	}
	

}
