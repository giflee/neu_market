package com.tarena.redis;

import java.util.Iterator;
import java.util.Map;

import com.tarena.util.SystemConstant;

import redis.clients.jedis.Jedis;

public class RedisTools implements SystemConstant {

	public static String get(String key) {
		
		Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
		
		String value = jedis.get(key);
		jedis.disconnect();
		return value;
	}
	public static String getPassword(String name) {
		
		Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
		
		String value = jedis.get(USER_NAME_COL+name);
		jedis.disconnect();
		return value;
	}
	public static String getId(String name) {
		
		Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
		
		String value = jedis.get(USER_NAME_ID_COL+name);
		jedis.disconnect();
		return value;
	}
	public static void set(String key, String value) {
		
		Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
		
		jedis.set(key,value);
		jedis.disconnect();
	}
	
	public static void setUsers(Map<String,String> users){
		Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
		Iterator<String> iterator = users.keySet().iterator();
		while(iterator.hasNext()){
			String name = iterator.next();
			String password = users.get(name);
			name = USER_NAME_COL+name;
			jedis.set(name, password);
		}
		jedis.disconnect();
	}
	public static void setNameIds(Map<String,String> nameIds){
		Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
		Iterator<String> iterator = nameIds.keySet().iterator();
		while(iterator.hasNext()){
			String name = iterator.next();
			String id = nameIds.get(name);
			name = USER_NAME_ID_COL+name;
			jedis.set(name, id);
		}
		jedis.disconnect();
	}
	public static void main(String[] args) {
		Map<String, String> users = MySqlTools.getUsers();
		RedisTools.setUsers(users);
		
		Map<String, String> nameIds = MySqlTools.getNameIds();
		RedisTools.setNameIds(nameIds);
	}

}
