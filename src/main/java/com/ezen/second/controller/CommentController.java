package com.ezen.second.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.second.domain.CommentVO;
import com.ezen.second.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/comment/*")
@Slf4j
@Controller
public class CommentController {
	
	@Inject
	private CommentService csv;
	
	@PostMapping(value="/{bno}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@PathVariable("bno")int bno, @RequestBody CommentVO cvo){
		log.info("comment post : "+bno);
		int isUp = csv.register(cvo);
		log.info("register isUp : "+(isUp>0?"ok":"fail"));
		
		return isUp>0? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	// res entity ( 들어갈 값이 3개 status / body / header 
	@GetMapping(value="/{bno}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CommentVO>> spread(@PathVariable("bno")int bno){
		
		log.info(">>> comment List bno : "+bno);
		List<CommentVO> list = csv.getList(bno);
		
		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK); 
	}
	
	@PutMapping(value="/{cno}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> update(@PathVariable("cno")int cno, @RequestBody CommentVO cvo){
		log.info("comment update : "+cno);
		
		int isUp = csv.update(cvo);
		log.info("update isUp : "+(isUp>0?"ok":"fail"));
		
		return isUp>0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/{cno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("cno")int cno){
		log.info("comment remove : "+cno);
		
		int isUp = csv.remove(cno);
		log.info("remove isUp : " + (isUp>0?"ok":"fail"));
		
		return isUp>0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
