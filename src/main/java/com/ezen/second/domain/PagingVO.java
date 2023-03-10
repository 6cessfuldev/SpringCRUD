package com.ezen.second.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingVO {
	
	private int pageNo;
	private int qty;
	
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	public int getPageStart() {
		return (this.pageNo-1)*this.qty;
	}
	
}
