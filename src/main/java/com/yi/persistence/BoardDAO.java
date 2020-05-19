package com.yi.persistence;

import java.util.List;

import com.yi.domain.BoardVO;
import com.yi.domain.Criteria;
import com.yi.domain.SearchCriteria;

public interface BoardDAO {
    public void insert(BoardVO vo) throws Exception;
    public BoardVO readByNo(int bno)throws Exception;
    public List<BoardVO> list()throws Exception;
    public void update(BoardVO vo)throws Exception;
    public void delete(int bno)throws Exception;
    public List<BoardVO> listPage(int page)throws Exception;
    public List<BoardVO> listCriteria(Criteria cri)throws Exception;
    public List<BoardVO> listSearchCriteria(SearchCriteria cri)throws Exception;
    public int totalCount() throws Exception;
    public int totalSearchCount(SearchCriteria cri) throws Exception;
    
    public void updateReplyCnt(int amount, int bno)throws Exception;
    
    
    public void insertAttach(String fullname) throws Exception;
    public BoardVO readAttachByBno(int bno) throws Exception;
    //사진 지우기
    public void delPic(String fullname) throws Exception;
    
    //업데이트 하는데 필요한 부분 
    public void insertAttachNUpdate(String fullName ,int bno)throws Exception;
    public void deleteAttach(String fullName);
    public void deleteAttachByBno(int bno);
    
 }
