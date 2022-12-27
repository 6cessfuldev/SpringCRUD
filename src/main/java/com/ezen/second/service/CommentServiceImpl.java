package com.ezen.second.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.second.domain.CommentVO;
import com.ezen.second.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

	@Inject
	private CommentDAO cdao;
	
	@Override
	public int register(CommentVO cvo) {
		log.info("cService register");
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		log.info("cService getList");
		return cdao.selectList(bno);
	}

	@Override
	public int update(CommentVO cvo) {
		log.info("cService getList");
		return cdao.update(cvo);
	}

	@Override
	public int remove(int cno) {
		log.info("cService remove");
		return cdao.delete(cno);
	}

}
