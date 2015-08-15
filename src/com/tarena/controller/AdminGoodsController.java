package com.tarena.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tarena.entity.Goods;
import com.tarena.entity.JsonWrapper;
import com.tarena.entity.PageInfo;
import com.tarena.pager.Pager;
import com.tarena.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class AdminGoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/editGood.do")
	public @ResponseBody  JSONObject editGood(String goods_id,String goods_name,double goods_price, MultipartFile upLoadFile_edit,  HttpServletRequest request, HttpServletResponse response){
		if(upLoadFile_edit.isEmpty()){
			System.out.println(goodsService.findById(goods_id).getGoods_pic());
			goodsService.updateGood(goods_id, goods_name, goods_price, goodsService.findById(goods_id).getGoods_pic());
			return new JsonWrapper.Builder(true, null, "修改成功", null).build();
		}else{
			MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request; 
			  MultipartFile file  =  multipartRequest.getFile("upLoadFile_edit");
			  String realPath = request.getSession().getServletContext().getRealPath("/");
	      		String attachment_name = file.getOriginalFilename();
	      		String str1=attachment_name.substring(attachment_name.indexOf("."));
	      		System.out.println(request.getContextPath());
				String url ="/images/"+goods_name+str1;
				File file1=new File(realPath+url);
		        try{
		        	System.out.println(goods_id);
		        	System.out.println(goods_name);
		        	System.out.println(goods_price);
		        	System.out.println(url);
		        	FileUtils.writeByteArrayToFile(file1, file.getBytes());
		        	goodsService.updateGood(goods_id, goods_name, goods_price, url);
		        	return new JsonWrapper.Builder(true, null, "修改成功", null).build();
		        }catch(Exception ex){
		            ex.printStackTrace();
		            return new JsonWrapper.Builder(false, "1", "修改失败", null).build();
		        }  
		}
	}
	
	
	@RequestMapping("/addGoods.do")
	public @ResponseBody JSONObject addGoods(String type_id,String goods_name,double goods_price, MultipartFile upLoadFile_add,  HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(!upLoadFile_add.isEmpty()){
			 MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
		        MultipartFile file  =  multipartRequest.getFile("upLoadFile_add");
		        String realPath = request.getSession().getServletContext().getRealPath("/");
	      		String attachment_name = file.getOriginalFilename();
	      		String str1=attachment_name.substring(attachment_name.indexOf("."));
	      		System.out.println(request.getContextPath());
				String url ="/images/"+goods_name+str1;
				File file1=new File(realPath+url);
				System.out.println(url);
		        try{
		        	FileUtils.writeByteArrayToFile(file1, file.getBytes());
		        	goodsService.addGood(type_id, goods_name, goods_price,url);
		        	return new JsonWrapper.Builder(true, null, "添加成功", null).build();
		        }catch(Exception ex){
		            ex.printStackTrace();
		            return new JsonWrapper.Builder(false, "1", "添加失败", null).build();
		        }  
		}
		return new JsonWrapper.Builder(false,"2", "必须上传图片", null).build();
	}
	
	@RequestMapping("/find.do")
	public @ResponseBody JSONObject find(String type_id,String goods_name,String page1){
		int currentPage = page1==null?1:Integer.parseInt(page1);
		int pageSize = 3;
		if (currentPage<=0){
			currentPage =1;
		}
		int currentResult = (currentPage-1) * pageSize;
		
		PageInfo page = new PageInfo();
		page.setShowCount(pageSize);
		page.setCurrentResult(currentResult);
		
		System.out.println("类型ID"+type_id+"商品"+goods_name);
		List<Goods> goodsList=null;
		if(type_id.equals("000")||type_id.equals("")){
			type_id=null;
		}
		goodsList = goodsService.findGoods(page,type_id, goods_name);
		
		int totalCount = page.getTotalResult();
		JSONObject content = new JSONObject();
		content.put("total", totalCount);
		content.put("size", pageSize);
		content.put("goods", goodsList);
		return new JsonWrapper.Builder(true, null, "查找成功", content).build();
	}
	
	@RequestMapping("/delete.do")
	public @ResponseBody JSONObject delete(String goods_id){
		goodsService.deleteGood(goods_id);
		return new JsonWrapper.Builder(true, null, "删除成功", null).build();
	}
	
	
}
