package com.ezen.second.repository;

import com.ezen.second.domain.UserVO;

public interface UserDAO {

	int insert(UserVO user);

	int selectCntById(String id);

	UserVO selectUserById(String id);

}
