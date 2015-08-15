package com.tarena.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Problem implements Serializable {

	private String problem_id;
	private String problem_title;
	private String problem_body;
	private Timestamp problem_time;
	public String getProblem_id() {
		return problem_id;
	}
	public void setProblem_id(String problem_id) {
		this.problem_id = problem_id;
	}
	public String getProblem_title() {
		return problem_title;
	}
	public void setProblem_title(String problem_title) {
		this.problem_title = problem_title;
	}
	public String getProblem_body() {
		return problem_body;
	}
	public void setProblem_body(String problem_body) {
		this.problem_body = problem_body;
	}
	public Timestamp getProblem_time() {
		return problem_time;
	}
	public void setProblem_time(Timestamp problem_time) {
		this.problem_time = problem_time;
	}
	@Override
	public String toString() {
		return "Problem [problem_id=" + problem_id + ", problem_title="
				+ problem_title + ", problem_body=" + problem_body
				+ ", problem_time=" + problem_time + "]";
	}
}
