package com.ezen.second.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.second.domain.BoardDTO;
import com.ezen.second.domain.BoardVO;
import com.ezen.second.domain.FileVO;
import com.ezen.second.domain.PagingVO;
import com.ezen.second.repository.BoardDAO;
import com.ezen.second.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;
	
	@Inject
	private FileDAO fdao;
	
	@Override
	public int register(BoardDTO boardDTO) {
		log.info("service register");
		int isOk = bdao.insert(boardDTO.getBvo());
		if(isOk >0 && boardDTO.getFList().size()>0) {
			int bno = bdao.selectOneBno();
			for (FileVO fvo : boardDTO.getFList()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}
		return isOk;
	}
	
	@Override
	public int register2(BoardDTO boardDTO) {
		int isOk = bdao.insert2(boardDTO.getBvo());
		log.info("bserivceimpl bno:"+boardDTO.getBvo().getBno());
		
		if(isOk >0 && boardDTO.getFList().size()>0) {
			int bno = boardDTO.getBvo().getBno();
			for (FileVO fvo : boardDTO.getFList()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}
		
		return isOk;
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
	public BoardDTO getDetail(int bno) {
		
		BoardVO bvo = bdao.selectBoard(bno);
		List<FileVO> fvo = fdao.selectFile(bno);
		
		return new BoardDTO(bvo, fvo);
	}

	@Override
	public int modify(BoardVO board) {
		
		return bdao.update(board);
	}

	@Override
	public int totalCount() {
	
		return bdao.totalCount();
	}

	@Override
	public int remove(int bno) {
		
		return bdao.delete(bno);
	}

	@Override
	public int addFile(int bno, List<FileVO> fList) {
		
		log.info("bno "+bno+" add file ");
		
		int isOk = 1;
		if(isOk >0 && fList.size()>0) {
			for (FileVO fvo : fList) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}
		
		return isOk;
	}

	@Override
	public int deleteFile(String uuid) {
		
		return fdao.deleteFile(uuid);
	}

	@Override
	public FileVO getFileByUuid(String uuid) {
		
		return fdao.selectFileByUuid(uuid);
	}

}
