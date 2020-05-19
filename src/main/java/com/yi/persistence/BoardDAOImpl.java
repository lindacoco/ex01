package com.yi.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yi.domain.BoardVO;
import com.yi.domain.Criteria;
import com.yi.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	
	private static final String namespace = "mappers.BoardMapper.";
	
	
	@Autowired
	private SqlSession sqlSession;
	@Override
	public void insert(BoardVO vo) throws Exception {
		sqlSession.insert(namespace+"insert",vo);

	}

	@Override
	public BoardVO readByNo(int bno) throws Exception {
		return sqlSession.selectOne(namespace+"readByNo", bno);
	}

	@Override
	public List<BoardVO> list() throws Exception {
		return sqlSession.selectList(namespace+"list");
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		sqlSession.update(namespace+"update",vo);
		

	}

	@Override
	public void delete(int bno) throws Exception {
        sqlSession.delete(namespace+"delete",bno);
        
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		// TODO Auto-generated method stub
		// 1 - 0 , 2 -10 
		//페이지 처리하면 편하다
		if(page < 0) {
			page = 1;
		}
		page = (page -1)*10;
		
		return sqlSession.selectList(namespace+"listPage",page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return sqlSession.selectList(namespace+"listCriteria",cri);
	}

	@Override
	public int totalCount() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"totalCount");
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return sqlSession.selectList(namespace+"listSearchCriteria",cri);
	}

	@Override
	public int totalSearchCount(SearchCriteria cri) throws Exception {
		return sqlSession.selectOne(namespace+"totalSearchCount",cri);
	}

	@Override
	public void updateReplyCnt(int amount, int bno) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("amount",amount);
		map.put("bno",bno);
		
		sqlSession.update(namespace+"updateReplyCnt",map);
		
	}

	@Override
	public void insertAttach(String fullname) throws Exception {
		sqlSession.insert(namespace+"insertAttach",fullname);
		
	}

	@Override
	public BoardVO readAttachByBno(int bno) throws Exception {
		return sqlSession.selectOne(namespace+"readAttachByBno",bno);
		
	}

	@Override
	public void delPic(String fullname) throws Exception {
		sqlSession.delete(namespace+"delPic",fullname);
		
	}

	@Override
	public void insertAttachNUpdate(String fullName, int bno) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fullName",fullName);
		map.put("bno",bno);
		sqlSession.insert(namespace+"insertAttachNUpdate",map);
		
	}

	@Override
	public void deleteAttach(String fullName) {
		sqlSession.delete(namespace+"deleteAttach",fullName);
		
	}

	@Override
	public void deleteAttachByBno(int bno) {
		sqlSession.delete(namespace+"deleteAttachByBno",bno);
		
	}
	
	

}
