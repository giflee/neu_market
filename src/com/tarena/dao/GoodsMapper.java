package com.tarena.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tarena.entity.Goods;
import com.tarena.entity.PageInfo;

public interface GoodsMapper {

	void addGoods(Goods goods);
	Goods findById(String id);
	String findTypeById(String id);
	List<Goods> findGoodsListPage(@Param("page") PageInfo page,@Param(value="type_id")String type_id,
			@Param(value="goods_name")String goods_name);//模糊匹配  分页查询
	void deleteGood(String goods_id);
	void updateGood(Goods goods);
	
	
	//caolei_code 20150727
		List<Goods> findTop();
		List<Goods> loadByType(String typeId);
		
		List<Goods> findPageByType(String typeId, int start,int pageSize);
		List<Goods> findPageByAll(int start,int pageSize);
		
		
		
		int findCountByType(String typeId);
		int findCount();
}
