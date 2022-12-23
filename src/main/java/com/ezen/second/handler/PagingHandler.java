package com.ezen.second.handler;

import com.ezen.second.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingHandler {

	private int startPage;
	private int endPage;
	private int totalCount;
	
	private boolean prev, next;
	
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		
		this.pgvo=pgvo;
		this.totalCount=totalCount;
		int realEndPage = totalCount/pgvo.getQty()+1;
		
		this.endPage = (int)Math.ceil(pgvo.getPageNo()/(pgvo.getQty()*1.0))*pgvo.getQty();
		this.startPage = endPage-pgvo.getQty()+1;
		
		if(endPage>realEndPage) {
			endPage = realEndPage;
		}
		
		if(startPage>1) prev = true;
		if(endPage<realEndPage) {
			next = true;
		}
	}
	
}
