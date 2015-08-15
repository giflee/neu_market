package com.tarena.pager;

import java.util.List;

public class Pager<E> {
	
	/*
	 * 分页参数
	 * **/
	private int offset;
	private int pageSize;
	private int total;
	private List<E> data;
	
	public Pager(){}

	public Pager(int offset, int pageSize, int total) {
		super();
		this.offset = offset;
		this.pageSize=pageSize;
		this.total = total;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Pager [offset=" + offset + ", pageSize=" + pageSize
				+ ", total=" + total + ", data=" + data + "]";
	}
	
}
