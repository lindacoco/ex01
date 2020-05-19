package com.yi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yi.domain.Criteria;
import com.yi.domain.PageMaker;
import com.yi.domain.ReplyVO;
import com.yi.service.ReplyService;



@RestController
@RequestMapping("/replies/*")
public class ReplyController {
	@Autowired
    private ReplyService service;
	
	//등록  포스트 방식으로
	
	//결과가 문구로
	@RequestMapping(value = "",method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		//리퀘스트바디 : 바디에 실려오는 json데이터를 class객체에 알맞게 주입을 시킴
		//ajax용일때만 사용한다 
		System.out.println(vo);
		
		ResponseEntity<String> entity = null;
		
		try {
			service.insert(vo);
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST); //400에러
					
		}
		return entity;
		
	}
	
	//replied/all?bno=4095 주소자체에 매개변수를 실어서 오는 것 
	@RequestMapping(value = "/all/{bno}",method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno){ //게시글 번호를 받아와야한다
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			List<ReplyVO> list = service.list(bno);
			entity = new ResponseEntity<List<ReplyVO>>(list,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	//업데이트
	@RequestMapping(value = "/{rno}",method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;

		System.out.println(vo);
		System.out.println(rno);
		
		
		try {
			vo.setRno(rno); //주소줄에서 넘어온 rno번호를 넣음 
			service.update(vo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("FAIL",HttpStatus.BAD_REQUEST); //400에러
					
		}
		return entity;
		
	}
	
	@RequestMapping(value = "/{rno}",method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rno") int rno){
		ResponseEntity<String> entity = null;

		try {
			service.delete(rno);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("FAIL",HttpStatus.BAD_REQUEST); //400에러
					
		}
		return entity;
		
		
	}
	
	@RequestMapping(value = "/{bno}/{page}",method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> listPage(@PathVariable("bno") int bno, @PathVariable("page")int page){ //게시글 번호를 받아와야한다
		ResponseEntity<Map<String,Object>> entity = null;
	
		
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			//계산하는 것도 보내줘야한다 setTotalCount
			pageMaker.setTotalCount(service.totalCount(bno)); //총 게시글의 개수를 넣어야한다 
			
			
			List<ReplyVO> list = service.listPage(bno,cri);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("pageMaker",pageMaker);
			entity = new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}

	
	
}
