package com.tarena.test;

import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import com.tarena.redis.MySqlTools;
import com.tarena.redis.RedisTools;

public class TestCase {
	
	@Test
	public void testRedisGet(){
		String value = RedisTools.get("name1");
		System.out.println(value);
	}
	@Test
	public void testRedisGetPwd(){
		String value = RedisTools.getPassword("qqq");
		System.out.println(value);
	}
	@Test
	public void testRedisGetId(){
		
		String value="";
		try {
			value = RedisTools.getId("qqq");
		} catch (Exception e) {
			//e.printStackTrace();
		}
		System.out.println(value);
	}
	@Test
	public void testRedisSet(){
		RedisTools.set("name","jerry");
	}
	@Test
	public void testMySqlGet(){
		String password = MySqlTools.getPassword("qqq");
		System.out.println(password);
	}
	@Test
	public void testMySqlGetUsers(){
		Map<String, String> users = MySqlTools.getUsers();
		Iterator<String> iterator = users.keySet().iterator();
		while(iterator.hasNext()){
			String name = iterator.next();
			String password = users.get(name);
			System.out.println("name : "+name+"  password : "+password);
		}
	}
	@Test
	public void testMySqlGetNameIds(){
		Map<String, String> nameIds = MySqlTools.getNameIds();
		Iterator<String> iterator = nameIds.keySet().iterator();
		while(iterator.hasNext()){
			String name = iterator.next();
			String id = nameIds.get(name);
			System.out.println("name : "+name+"  id : "+id);
		}
	}
	@Test
	public void testRedisSetUsers(){
		Map<String, String> users = MySqlTools.getUsers();
		RedisTools.setUsers(users);
	}
	@Test
	public void testRedisSetNameIds(){
		Map<String, String> nameIds = MySqlTools.getNameIds();
		RedisTools.setNameIds(nameIds);
	}
}
