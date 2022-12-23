package com.ezen.second.service;

import com.ezen.second.domain.UserVO;

public interface UserService {

	int register(UserVO user);

	int isExist(String id);

	UserVO getUserById(String id);

}
