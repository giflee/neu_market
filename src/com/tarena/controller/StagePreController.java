package com.tarena.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.JsonWrapper;
import com.tarena.entity.Order;
import com.tarena.entity.OrderGoods;
import com.tarena.entity.User;
import com.tarena.service.LoginService;
import com.tarena.service.OrderService;
import com.tarena.util.ImageUtil;
import com.tarena.util.Md5Util;
@Controller
@RequestMapping("/pre")
public class StagePreController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private OrderService orderService;
	@RequestMapping("/login.do")
	public @ResponseBody JSONObject login(String name,String pwd,HttpSession session){
		System.out.println(name+pwd);
		User user = loginService.login(name, pwd);
		if(user==null){
			return new JsonWrapper.Builder(false, "1", "用户不存在", null).build();
		}else if(!user.getUser_password().equals(Md5Util.md5(pwd))){
			return new JsonWrapper.Builder(false, "2", "密码错误", null).build();
		}else{
			JSONObject jo = new JSONObject();
			jo.put("user", user.getUser_name());
			session.setAttribute("user", user.getUser_name());
			session.setAttribute("user_id", user.getUser_id());
			return new JsonWrapper.Builder(true, null, "登陆成功", jo).build();
		}
	}
	

	@RequestMapping("/showImage.do")
	public void createImage(
			HttpServletResponse response, HttpSession session)
			throws Exception {
		Map<String, BufferedImage> imageMap = ImageUtil.createImage();
		String code = imageMap.keySet().iterator().next();
		session.setAttribute("imageCode", code);
		
		BufferedImage image = imageMap.get(code);
		
		response.setContentType("image/jpeg");
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "jpeg", os);
		os.close();
	}
	
	@RequestMapping("/getCode.do")
	public @ResponseBody JSONObject Validate(HttpSession session){
			String code = (String) session.getAttribute("imageCode");
			JSONObject jo =new JSONObject();
			jo.put("code", code);
			return new JsonWrapper.Builder(true, null, null, jo).build();
	}
	
	@RequestMapping("/register.do")
	public @ResponseBody JSONObject register(String name,String email,String pwd){
		loginService.register(name, pwd, email);
		return new JsonWrapper.Builder(true, null, null, null).build();
	}
	
	@RequestMapping("/logout.do")
	public @ResponseBody JSONObject logout(HttpSession session){
		System.out.println(session.getAttribute("user"));
		session.invalidate();
		System.out.println("-----------------");
		return new JsonWrapper.Builder(true, null, null, null).build();
	}
	
	@RequestMapping("/findOrders.do")
	public @ResponseBody JSONObject findOrders(HttpSession session){
		
		List<Order> list = orderService.findOrdersPre((String)session.getAttribute("user_id"));
		JSONObject jo = new JSONObject();
		jo.put("orderList", list);
		return new JsonWrapper.Builder(true, null, "查找成功", jo).build();
	}
	
	@RequestMapping("/findOrderDetail.do")
	public @ResponseBody JSONObject findDetails(String uri){
		Order  order =  orderService.findOrderById(uri);
		List<OrderGoods> list = orderService.findOrderGoodsById(uri);
		JSONObject jo = new JSONObject();
		jo.put("order", order);
		jo.put("detail", list);
		return new JsonWrapper.Builder(true, null, "查找成功", jo).build();
	}
	
	@RequestMapping("/shouhuo.do")
	public @ResponseBody JSONObject shouhuo(String id){
		Order order = orderService.findOrderById(id);
		order.setOrder_status(0);
		System.out.println(order);
		orderService.update(order);
		return new JsonWrapper.Builder(true, null, "确认收货", null).build();
	}
}
