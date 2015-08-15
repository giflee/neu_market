package com.tarena.service;

import java.util.List;

import com.tarena.entity.Problem;

public interface ProblemService {

	List<Problem> find();
	
	Problem add(String problem_title,String problem_body);
	
	void delete(String problem_id);
	
	void update(Problem problem);
	
	Problem findById(String problem_id);
}
