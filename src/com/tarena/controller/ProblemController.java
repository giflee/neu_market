package com.tarena.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.JsonWrapper;
import com.tarena.entity.Problem;
import com.tarena.service.ProblemService;
@Controller
@RequestMapping("/problem")
public class ProblemController implements Serializable {

	@Resource
	private ProblemService problemService;
	
	@RequestMapping("/find.do")
	@ResponseBody
	public JSONObject find(){
		JSONObject content = new JSONObject();
		List<Problem> problemList = problemService.find();
		content.put("problemList", problemList);
		return new JsonWrapper.Builder(true, null, null, content).build();
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public JSONObject add(String problem_title,String problem_body){
		JSONObject content = new JSONObject();
		Problem problem= problemService.add(problem_title, problem_body);
		content.put("problem", problem);
		return new JsonWrapper.Builder(true, null, "添加成功", content).build();
		
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public JSONObject delete(String problem_id){
		problemService.delete(problem_id);
		return new JsonWrapper.Builder(true, null, "删除成功", null).build();
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public JSONObject update(String problem_id,String problem_title,String problem_body){
		Problem problem = problemService.findById(problem_id);
		problem.setProblem_title(problem_title);
		problem.setProblem_body(problem_body);
		problemService.update(problem);
		return new JsonWrapper.Builder(true, null, "修改成功", null).build();
	}
	

	
//	@RequestMapping("/findById.do")
//	@ResponseBody
//	public JSONObject findById(String problem_id){
//		JSONObject content = new JSONObject();
//		Problem problem = problemService.findById(problem_id);
//		content.put("problem", problem);
//		return new JsonWrapper.Builder(true, null, null, content).build();
//	}
}
