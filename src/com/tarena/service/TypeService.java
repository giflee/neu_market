package com.tarena.service;

import java.util.List;

import com.tarena.entity.Type;

public interface TypeService {

	List<Type> getAllType();
	Integer getTypeNum(String goodsId);//获取该商品类别下的商品种类数量
	void addType(String type_name);//新增商品类别 
	List<Type> findTypes(String type_name);//模糊查询
	void deleteType(String type_id);
	

}
