package com.tarena.service.Impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarena.dao.GoodsMapper;
import com.tarena.entity.Goods;
import com.tarena.entity.PageInfo;
import com.tarena.entity.Type;
import com.tarena.service.GoodsService;
import com.tarena.util.BaseContent;

@Service
public class GoodsServiceImpl implements GoodsService,BaseContent{

	@Autowired
	private GoodsMapper goodsMapper;
	@Override
	public void addGood(String type_id, String name, double price,String url) {
		Goods goods = new Goods();
		Type type = new Type();
		type.setType_id(type_id);
		goods.setType(type);
		goods.setGoods_id(UUID.randomUUID().toString());
		goods.setGoods_name(name);
		goods.setGoods_price(price);
		goods.setGoods_pic(url);
		goods.setGoods_creatime(new Timestamp(System.currentTimeMillis()));
		goodsMapper.addGoods(goods);
	}
	@Override
	public String findTypeById(String id) {
		return goodsMapper.findTypeById(id);
	}
	@Override
	public List<Goods> findGoods(PageInfo page,String type_id, String goods_name) {
		return goodsMapper.findGoodsListPage(page,type_id, goods_name);
	}
	@Override
	public void deleteGood(String goods_id) {
		goodsMapper.deleteGood(goods_id);
	}
	@Override
	public Goods findById(String id) {
		return goodsMapper.findById(id);
	}
	@Override
	public void updateGood(String id, String name, double price, String url) {
		Goods good = goodsMapper.findById(id);
		good.setType(good.getType());
		good.setGoods_name(name);
		good.setGoods_pic(url);
		good.setGoods_price(price);
		System.out.println(good);
		goodsMapper.updateGood(good);
	}
	
	//曹磊
		@Override
		public List<Goods> loadTop() {
			
			return goodsMapper.findTop();
		}

		@Override
		public List<Goods> getByTid(String typeId) {
			
			return goodsMapper.loadByType(typeId);
		}
		
		
		@Override
		public List<Goods> getPageByType(String typeId, int currentPage) {
			
			int start = (currentPage-1)*STAGE_PAGE_SIZE;
			
			return goodsMapper.findPageByType(typeId, start, STAGE_PAGE_SIZE);
		}
		@Override
		public int getPagesNumByType(String typeId) {
			int num = goodsMapper.findCountByType(typeId);
			
			return num%STAGE_PAGE_SIZE==0?num/STAGE_PAGE_SIZE:num/STAGE_PAGE_SIZE+1;
		}
		@Override
		public List<Goods> getPageByAll(int currentPage) {
			
			int start = (currentPage-1)*STAGE_PAGE_SIZE;
			
			return goodsMapper.findPageByAll(start, STAGE_PAGE_SIZE);
		}
		@Override
		public int getPagesNumByAll() {
			int num = goodsMapper.findCount();
			
			return num%STAGE_PAGE_SIZE==0?num/STAGE_PAGE_SIZE:num/STAGE_PAGE_SIZE+1;
		}
		public static void main(String[] args) {
			System.out.println(10/3);
		}
	}
