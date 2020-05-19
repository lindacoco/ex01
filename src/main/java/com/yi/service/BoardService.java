package com.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yi.domain.BoardVO;
import com.yi.domain.Criteria;
import com.yi.domain.SearchCriteria;
import com.yi.persistence.BoardDAO;

@Service
public class BoardService {
   
	@Autowired
	BoardDAO dao;
	
	//등록을 위한 함수
	@Transactional  //알아서 롤백처리까지하는거 
	public void create(BoardVO vo) throws Exception {
		dao.insert(vo);
		
		
		//파일 이름을 tbl_attach에 넣어줘야한다
		//파일 이름은 vo.getFiles 안에 들어있다 
		//vo.getFiles();
		
		for(String file : vo.getFiles()) {
			dao.insertAttach(file); //LAST_INSERT_ID()
		}
	}
	
	public BoardVO readByNo(int bno) throws Exception {
		return dao.readByNo(bno);
	}
	
	public List<BoardVO> list() throws Exception {
		return dao.list();
	}
	
	public void update(BoardVO vo)throws Exception{
		dao.update(vo);
	}
	
	@Transactional
	public void updateAndAddAttach(BoardVO vo) throws Exception {
		dao.update(vo);
		
		
		for(String fName : vo.getFiles()) {
			dao.insertAttachNUpdate(fName, vo.getBno());
		}

	}
	@Transactional
	public void updateAndDeleteAttach(BoardVO vo) throws Exception{
               dao.update(vo);
		
		for(String fName : vo.getFiles()) {
			dao.deleteAttach(fName);
		}

	}
	
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}
	
	@Transactional
	public void deleteWithAttach(int bno) throws Exception{		
	    dao.deleteAttachByBno(bno);
	    dao.delete(bno);
		
	}
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception{
		return dao.listCriteria(cri);
	}
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception{
		return dao.listSearchCriteria(cri);
	}
	
	public int totalCount() throws Exception {
		return dao.totalCount();
	}
	
	public int totalSearchCount(SearchCriteria cri) throws Exception {
		return dao.totalSearchCount(cri);
	}
	
	public BoardVO readAttachByBno(int bno) throws Exception {
		return dao.readAttachByBno(bno);
		
	}
	public void delPic(String fullname) throws Exception {
		dao.delPic(fullname);
		
	}
}
