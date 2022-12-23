package com.ezen.second.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.second.domain.BoardVO;
import com.ezen.second.domain.PagingVO;
import com.ezen.second.handler.PagingHandler;
import com.ezen.second.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	@Inject
	private BoardService bsv;
	
	@GetMapping("/register")
	public String registerGET(HttpServletRequest req) {
		if(req.getSession().getAttribute("user") ==null) {
			return "/member/login";
		}
		return "/board/register";
	}
	
	@PostMapping("/register")
	public ModelAndView registerPOST(BoardVO bvo, ModelAndView mv) {
		
		int isOk = bsv.register(bvo);
		log.info("isOk "+(isOk>0 ? "success":"fail"));
		
		mv.addObject("result", isOk);
		mv.setViewName("redirect:/");
		
		return mv;
	}
	
	@GetMapping("/list")
	public ModelAndView listGET(ModelAndView mv, PagingVO pgvo) {
		
		int totalCount = bsv.totalCount();
		PagingHandler pgh = new PagingHandler(pgvo, totalCount);
		mv.addObject("pgh", pgh);
		
		List<BoardVO> list = bsv.getList(pgvo);
		mv.addObject("list", list);
		mv.setViewName("/board/list");
		
		return mv;
	}
	
	@GetMapping("/detail")
	public ModelAndView DetailGET(ModelAndView mv, int bno) {
		
		log.info(bno+"");
		bsv.countUp(bno);
		BoardVO board = bsv.getDetail(bno);
		mv.addObject("board", board);
		mv.setViewName("/board/detail");
		
		return mv;
	}
	
	@GetMapping("/modify")
	public ModelAndView modifyGET(ModelAndView mv, int bno) {
		
		BoardVO board = bsv.getDetail(bno);
		mv.addObject("board", board);
		mv.setViewName("/board/modify");
		
		return mv;
	}
	
	@PostMapping("modify")
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) {
		
		log.info(board.toString());
		int isOk = bsv.modify(board);
		log.info("update "+(isOk>0 ? "success":"fail"));
		rttr.addAttribute("result", (isOk>0? 1:0));
		
		return "redirect:/board/detail?bno="+board.getBno();
	}
	
}
