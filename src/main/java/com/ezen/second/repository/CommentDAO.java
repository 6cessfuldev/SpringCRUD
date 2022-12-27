package com.ezen.second.repository;

import java.util.List;

import com.ezen.second.domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> selectList(int bno);

	int update(CommentVO cvo);

	int delete(int cno);

}
