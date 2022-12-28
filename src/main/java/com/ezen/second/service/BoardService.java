package com.ezen.second.service;

import java.util.List;

import com.ezen.second.domain.BoardDTO;
import com.ezen.second.domain.BoardVO;
import com.ezen.second.domain.FileVO;
import com.ezen.second.domain.PagingVO;

public interface BoardService {

	int register(BoardDTO boardDTO);

	List<BoardVO> getList(PagingVO pgvo);

	void countUp(int bno);

	BoardDTO getDetail(int bno);

	int modify(BoardVO board);

	int totalCount();

	int remove(int bno);

	int register2(BoardDTO boardDTO);

	int addFile(int i, List<FileVO> fList);

	int deleteFile(String uuid);

	FileVO getFileByUuid(String uuid);

}
