package com.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yi.domain.Criteria;
import com.yi.domain.ReplyVO;
import com.yi.persistence.BoardDAO;
import com.yi.persistence.ReplyDAO;

@Service
public class ReplyService {
	
	@Autowired
	ReplyDAO dao;
	
	@Autowired
	BoardDAO boardDao;
	
	@Transactional
	public void insert(ReplyVO vo) throws Exception{
		dao.insert(vo);
		boardDao.updateReplyCnt(1, vo.getBno());
	}
    public List<ReplyVO> list(int bno) throws Exception{ //게시글 번호가 있는 것을 가져와야함 
        return dao.list(bno);
    }
    
    @Transactional
    public void delete(int rno)throws Exception{
    	//rno하나로 dno를 찾도록 한다 함수가 있는지 확인 mapper
    	
    
    	ReplyVO vo = dao.selectByRno(rno);
    	dao.delete(rno);
    	boardDao.updateReplyCnt(-1, vo.getBno());
    	
    	
    }
    public void update(ReplyVO vo) throws Exception{
    	dao.update(vo);
    	
    }
    
    public List<ReplyVO> listPage(int bno, Criteria cri) throws Exception{ //댓글에도 리스트 
        return dao.listPage(bno,cri);
    }
    
    public int totalCount(int bno) throws Exception{
    	return dao.totalCount(bno);
    }
}
