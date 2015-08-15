package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Problem;

public interface ProblemMapper {

	List<Problem> find();
	
	void save(Problem problem);
	
	void delete(String problem_id);
	
	void update(Problem problem);
	
	Problem findById(String problem_id);
}
