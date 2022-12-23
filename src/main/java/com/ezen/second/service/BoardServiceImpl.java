package com.ezen.second.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.second.domain.BoardVO;
import com.ezen.second.domain.PagingVO;
import com.ezen.second.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;
	
	@Override
	public int register(BoardVO bvo) {
		log.info("service register");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info("service getList");
		return bdao.selectList(pgvo);
	}

	@Override
	public void countUp(int bno) {
		
		bdao.countUp(bno);		
	}

	@Override
	public BoardVO getDetail(int bno) {
		
		return bdao.selectBoard(bno);
	}

	@Override
	public int modify(BoardVO board) {
		
		return bdao.update(board);
	}

	@Override
	public int totalCount() {
	
		return bdao.totalCount();
	}
	
}
