package com.ezen.second.repository;

import java.util.List;

import com.ezen.second.domain.BoardVO;
import com.ezen.second.domain.PagingVO;

public interface BoardDAO {

	int selectOneBno();
	
	int insert(BoardVO bvo);

	List<BoardVO> selectList(PagingVO pgvo);

	void countUp(int bno);

	BoardVO selectBoard(int bno);

	int update(BoardVO board);

	int totalCount();

	int delete(int bno);

	int insert2(BoardVO bvo);

}
