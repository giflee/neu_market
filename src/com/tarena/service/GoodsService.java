package com.tarena.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tarena.entity.Goods;
import com.tarena.entity.PageInfo;
import com.tarena.pager.Pager;

public interface GoodsService {
	void addGood(String type_id,String name,double price,String url);	
	String findTypeById(String id);
	List<Goods> findGoods(PageInfo page,String type_id,String goods_name);
	void deleteGood(String goods_id);
	void updateGood(String id,String name,double price,String url);
	Goods findById(String id);
	
	
	//曹磊
	List<Goods> loadTop();
	List<Goods> getByTid(String typeId);
List<Goods> getPageByType(String typeId, int currentPage);
	
	int getPagesNumByType(String typeId);
	List<Goods> getPageByAll( int currentPage);
	
	int getPagesNumByAll();
}
