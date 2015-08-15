package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Type;

public interface TypeMapper {

	List<Type> findAll();//查找所有的商品类别
	Integer findTypeNum(String goodsId);//获取某类别下商品的数量
	void addType(Type type);//新增
	List<Type> findByName(String type_name);//模糊盘匹配
	void deleteType(String type_id);
}
