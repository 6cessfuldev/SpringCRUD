package com.ezen.second.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.second.domain.BoardDTO;
import com.ezen.second.domain.BoardVO;
import com.ezen.second.domain.FileVO;
import com.ezen.second.domain.PagingVO;
import com.ezen.second.handler.FileHandler;
import com.ezen.second.handler.PagingHandler;
import com.ezen.second.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	@Inject
	private BoardService bsv;
	@Inject
	private FileHandler fhd;
	
	@GetMapping("/register")
	public String registerGET(HttpServletRequest req) {
		if(req.getSession().getAttribute("user") ==null) {
			return "/member/login";
		}
		return "/board/register";
	}
	
	@PostMapping("/register")
	public ModelAndView registerPOST(BoardVO bvo, ModelAndView mv, @RequestParam(name="files" ,required =false)MultipartFile[] files) {
		
		log.info("bvo :"+bvo.toString());
		log.info("files :"+files.toString());
		List<FileVO> fList = null;
		if(files[0].getSize()>0) {
			fList = fhd.uploadFiles(files);
		}else {
			log.info("empty files");
			fList = new ArrayList<FileVO>();
		}		
		
		//int isOk = bsv.register(new BoardDTO(bvo, fList));
		int bno = bsv.register2(new BoardDTO(bvo, fList));
		log.info("bno :"+bno);
		int isOk = bno;
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
//		BoardVO board = bsv.getDetail(bno);
//		FileVO fvo = fdao.
		
		BoardDTO bdto = bsv.getDetail(bno);
		log.info(bdto.getBvo().toString());
//		log.info(bdto.getFList().get(0).toString());
		
		mv.addObject("board", bdto.getBvo());
		mv.addObject("fList", bdto.getFList());
		mv.setViewName("/board/detail");
		
		return mv;
	}
	
	@GetMapping("/modify")
	public ModelAndView modifyGET(ModelAndView mv, int bno) {
		
		BoardDTO bdto = bsv.getDetail(bno);
		mv.addObject("board", bdto.getBvo());
		mv.addObject("fList", bdto.getFList());
		mv.setViewName("/board/modify");
		
		return mv;
	}
	
	@PostMapping("/modify")
	public String modifyPOST(BoardVO board, @RequestParam(name="files" ,required =false)MultipartFile[] files, RedirectAttributes rttr) {
		
		log.info(board.toString());
		int isOk = bsv.modify(board);
		
		log.info("files :"+files.toString());
		List<FileVO> fList = null;
		if(files[0].getSize()>0) {
			fList = fhd.uploadFiles(files);
			isOk *= bsv.addFile(board.getBno(), fList);
			
		}else {
			log.info("empty files");
		}		
		
		log.info("update "+(isOk>0 ? "success":"fail"));
		rttr.addAttribute("result", (isOk>0? 1:0));
		
		return "redirect:/board/detail?bno="+board.getBno();
	}
	
	@GetMapping("/remove")
	public String removeGET(int bno, RedirectAttributes rttr) {
		log.info("remove "+bno);
		int isOk = bsv.remove(bno);
		log.info("reomve "+(isOk>0 ? "success":"fail"));
		rttr.addAttribute("result", "2");
		
		return "redirect:/board/list";
	}
	
	@DeleteMapping(value="file/{uuid}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> FileDelete(@PathVariable("uuid")String uuid){
		log.info("delete file : "+uuid);
		
		FileHandler fhd = new FileHandler();
		FileVO fvo = bsv.getFileByUuid(uuid);
		if(fvo ==null) {
			log.info("file is null");
		}else{
			log.info(fvo.toString());
		}
		int isOk = fhd.deleteFile(bsv.getFileByUuid(uuid)); 
		isOk *= bsv.deleteFile(uuid);
		
		log.info("delete "+(isOk>0?"success":"fail"));
		
		return isOk>0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

		
	}
	
}
