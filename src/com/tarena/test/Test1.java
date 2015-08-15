package com.tarena.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.service.GoodsService;

public class Test1 {

	@Test
	public void testFindAll() {
		ApplicationContext ctx =new  ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsService good= ctx.getBean(GoodsService.class);
		//System.out.println(good.findAll(2, 2));
	}

}
