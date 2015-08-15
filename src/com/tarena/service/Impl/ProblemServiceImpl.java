package com.tarena.service.Impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.ProblemMapper;
import com.tarena.entity.Problem;
import com.tarena.service.ProblemService;
@Service
public class ProblemServiceImpl implements Serializable, ProblemService {

	@Resource
	private ProblemMapper problemMapper;
	@Override
	public List<Problem> find() {
		// TODO Auto-generated method stub
		return problemMapper.find();
	}

	@Override
	public Problem add(String problem_title,String problem_body) {
		// TODO Auto-generated method stub
        Problem problem = new Problem();
        problem.setProblem_id(UUID.randomUUID().toString());
        problem.setProblem_time(new Timestamp(System.currentTimeMillis()));
        problem.setProblem_title(problem_title);
        problem.setProblem_body(problem_body);
		problemMapper.save(problem);
		return problem;
	}

	@Override
	public void delete(String problem_id) {
		// TODO Auto-generated method stub
        problemMapper.delete(problem_id);
	}

	@Override
	public void update(Problem problem) {
		// TODO Auto-generated method stub
        problemMapper.update(problem);
	}

	@Override
	public Problem findById(String problem_id) {
		// TODO Auto-generated method stub
		return problemMapper.findById(problem_id);
	}

}
