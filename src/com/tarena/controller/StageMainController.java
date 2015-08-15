package com.tarena.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.Goods;
import com.tarena.entity.JsonWrapper;
import com.tarena.entity.Order;
import com.tarena.entity.OrderGoods;
import com.tarena.entity.Type;
import com.tarena.service.GoodsService;
import com.tarena.service.OrderGoodsService;
import com.tarena.service.OrderService;
import com.tarena.service.TypeService;

@Controller
@RequestMapping("/stage/main")
public class StageMainController {
	
	@Autowired
	private TypeService typeService ;
	
	@Autowired
	private OrderGoodsService orderGoodsService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/load_goods.do")
	@ResponseBody
	public JSONObject loadGoods(){
		JSONObject jo = new JSONObject();
		List<Goods> goodsList = goodsService.loadTop();
		System.out.println(goodsList);
		jo.put("goodsList", goodsList);
		return new JsonWrapper.Builder(true, null, "加载成功", jo).build();
	}
	
	@RequestMapping("/load_types.do")
	@ResponseBody
	public JSONObject loadTypes(){
		JSONObject jo = new JSONObject();
		List<Type> allType = typeService.getAllType();
		jo.put("typeList",allType);
		return new JsonWrapper.Builder(true, null, null, jo).build();

	}
	
	/*
	 * 下订单
	 */
	@RequestMapping("/check_out.do")
	public String checkOut(HttpServletRequest request) throws ParseException{
		
		
		String oman = request.getParameter("oman");//收货人姓名
		String otel  = request.getParameter("otel");//收货人手机
		String oaddress = request.getParameter("oaddress");//收货地址
		String ocontent = request.getParameter("ocontent");//备注
		
		String days = request.getParameter("days");
		String times = request.getParameter("times");
		String receiveTime = days + " " + times;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date newdate = simpleDateFormat.parse(receiveTime);

		Order order = new Order();
		
		order.setOrder_id(UUID.randomUUID().toString());
		order.setAddress(oaddress);
		order.setOrder_status(1);
		order.setPay_way("货到付款");
		order.setOrder_time(new Timestamp(System.currentTimeMillis()));
		order.setReceive_time(new Timestamp(newdate.getTime()));
		order.setAddress_person(oman);
		order.setAddress_phone(otel);
		//set_price
		double price = 0;
		order.setOrder_mark(ocontent);
		List<OrderGoods> orderGoodsList = new ArrayList<OrderGoods>();
		int itemCount =Integer.parseInt( request.getParameter("itemCount"));
		for(int i = 1;i<=itemCount;i++){
			String itemName = request.getParameter("item_name_"+i);
			double itemPrice = Double.parseDouble( request.getParameter("item_price_"+i));
			int itemQuantity = Integer.parseInt( request.getParameter("item_quantity_"+i));
			
			price += itemPrice*itemQuantity;
			
			OrderGoods orderGoods = new OrderGoods();
			
			orderGoods.setOrder_goods_id(UUID.randomUUID().toString());
			orderGoods.setOrder(order);
			orderGoods.setGoods_name(itemName);
			orderGoods.setGood_price(itemPrice);
			orderGoods.setOrder_goods_num(itemQuantity);
			orderGoodsList.add(orderGoods);
		}
		
		order.setOrder_price(price);
		orderService.save(order);
		orderGoodsService.save(orderGoodsList);
		JSONObject jo = new JSONObject();
		jo.put("typeList","aa");
		
		String uid=(String) request.getSession().getAttribute("user_id");
		if(uid!=null){
			orderService.saveUO(order.getOrder_id(), uid);
		}
		return "redirect:/stage/main.html";
		
	}
	@RequestMapping("/load_goods_by_type_id.do")
	@ResponseBody
	public JSONObject loadGoodsByTid(String typeId){
		JSONObject jo = new JSONObject();
		List<Goods> goods = goodsService.getByTid(typeId);
		jo.put("goods",goods);
		return new JsonWrapper.Builder(true, null, null, jo).build();
		
	}
	@RequestMapping("/load_page_by_type_id.do")
	@ResponseBody
	public JSONObject loadPageByTid(int currentPage, String typeId){
		JSONObject jo = new JSONObject();
		List<Goods> goods = goodsService.getPageByType(typeId, currentPage);
		jo.put("goods",goods);
		int pageNum = goodsService.getPagesNumByType(typeId);
		jo.put("page_count", pageNum);
		return new JsonWrapper.Builder(true, null, null, jo).build();
		
	}
	@RequestMapping("/load_page_by_all.do")
	@ResponseBody
	public JSONObject loadPageByAll(int currentPage){
		JSONObject jo = new JSONObject();
		List<Goods> goods = goodsService.getPageByAll(currentPage);
		jo.put("goods",goods);
		int pageNum = goodsService.getPagesNumByAll();
		jo.put("page_count", pageNum);
		return new JsonWrapper.Builder(true, null, null, jo).build();
		
	}
}
