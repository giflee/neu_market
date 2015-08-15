package com.tarena.service.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarena.dao.TypeMapper;
import com.tarena.entity.Type;
import com.tarena.service.TypeService;
@Service
public class TypeServiceImpl implements TypeService{

	@Autowired
	private TypeMapper typeMapper;
	
	public List<Type> getAllType() {
		return typeMapper.findAll();
	}

	@Override
	public Integer getTypeNum(String goodsId) {
		return typeMapper.findTypeNum(goodsId);
	}

	@Override
	public void addType(String type_name) {
		Type type = new Type();
		type.setType_id(UUID.randomUUID().toString());
		type.setType_name(type_name);
		System.out.println(type);
		typeMapper.addType(type);
		
	}

	@Override
	public List<Type> findTypes(String type_name) {
		return typeMapper.findByName(type_name);
	}

	@Override
	public void deleteType(String type_id) {
		typeMapper.deleteType(type_id);
	}

}
