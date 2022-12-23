package com.ezen.second.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.second.controller.BoardController;
import com.ezen.second.domain.UserVO;
import com.ezen.second.repository.UserDAO;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO udao;
	
	@Override
	public int register(UserVO user) {
		log.info("service register");
		return udao.insert(user);
	}

	@Override
	public int isExist(String id) {
		log.info("service isExist");
		return udao.selectCntById(id);
	}

	@Override
	public UserVO getUserById(String id) {
		log.info("service getUserById");
		return udao.selectUserById(id);
	}

}
