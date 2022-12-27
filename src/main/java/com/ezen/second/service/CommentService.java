package com.ezen.second.service;

import java.util.List;

import com.ezen.second.domain.CommentVO;

public interface CommentService {

	int register(CommentVO cvo);

	List<CommentVO> getList(int bno);

	int update(CommentVO cvo);

	int remove(int cno);

}
