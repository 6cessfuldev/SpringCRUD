package com.ezen.second.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.second.domain.UserVO;
import com.ezen.second.service.UserService;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Inject
	private UserService usv; 
	
	@Inject
	private BCryptPasswordEncoder pwEncoder;
	

	@GetMapping({"/login","/join"})
	public void navGET() {
		
	}
	
	@PostMapping("/join")
	public ModelAndView joinPOST(UserVO user, ModelAndView mv) {
		
		int existed = usv.isExist(user.getId());
		
		if(existed >0) {
			log.info("arleady exist");
			mv.addObject("result", "0");
			mv.setViewName("/member/join");
			return mv;
		}
		
		String rawPw = user.getPw();
		String encodedPw = pwEncoder.encode(rawPw);
		user.setPw(encodedPw);
		
		int isOk = usv.register(user);
		log.info("isOk "+(isOk>0?"success":"fail"));
		mv.setViewName("/index");
		return mv;
	}
	
	@PostMapping("/login")
	public ModelAndView loginPOST(String id, String pw, ModelAndView mv, HttpServletRequest req) {
		
		UserVO user = usv.getUserById(id);
		
		if(user == null) {
			log.info("not exist ID");
			mv.addObject("result", '0');
			mv.setViewName("member/login");
			return mv;
		}
		
		if(!pwEncoder.matches(pw, user.getPw())) {
			mv.addObject("result", "-1");
			mv.setViewName("member/login");
			return mv;
		}
		
		
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		mv.setViewName("/index");
		
		return mv;
	}
	
	@GetMapping("/logout")
	public String logoutGET(HttpServletRequest req) {
		req.getSession().invalidate();
		return "/index";
	}
	
}
