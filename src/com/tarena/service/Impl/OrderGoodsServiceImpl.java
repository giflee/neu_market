package com.tarena.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.OrderGoodsMapper;
import com.tarena.entity.OrderGoods;
import com.tarena.service.OrderGoodsService;


@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {
	
	@Resource
	private	OrderGoodsMapper orderGoodsMapper;

	@Override
	public void save(List<OrderGoods> orderGoods) {
		for (int i = 0; i < orderGoods.size(); i++){
			orderGoodsMapper.save(orderGoods.get(i));
		}
	}

}
