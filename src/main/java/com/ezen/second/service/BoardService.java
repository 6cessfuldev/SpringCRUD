package com.ezen.second.service;

import java.util.List;

import com.ezen.second.domain.BoardVO;
import com.ezen.second.domain.PagingVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	void countUp(int bno);

	BoardVO getDetail(int bno);

	int modify(BoardVO board);

	int totalCount();

}
