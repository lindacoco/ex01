package com.yi.ex01;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yi.domain.BoardVO;
import com.yi.domain.Criteria;
import com.yi.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDAOTest {
	
	@Autowired
	private BoardDAO dao;
	
	
	@Test
	public void testDAO() {
		System.out.println(dao);
	}
	
	@Test
	public void test1insert() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setTitle("아이스크림");
		vo.setContent("먹고싶어");
		vo.setWriter("바닐라코코");
		dao.insert(vo);
		
	}
	@Test
	public void test2ReadBoard() throws Exception{
		BoardVO vo = dao.readByNo(2);
		System.out.println(vo);
	}
	@Test
	public void test3List() throws Exception {
		List<BoardVO> list = dao.list();
		System.out.println(list);
		
	}
	@Test
	public void test4Update() throws Exception {
		BoardVO vo = dao.readByNo(2);
		vo.setTitle("사탕도");
		vo.setContent("먹고싶은데");
		vo.setRegdate(new Date());
		dao.update(vo);
		
		System.out.println(vo);
	}
	@Test
	public void test5delete() throws Exception {
		dao.delete(3);
	}
	@Test
	public void test6ListPage() throws Exception{
		dao.listPage(1);
	}
	@Test
	public void test7ListCriteria() throws Exception{
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(5); //다섯개만 
		dao.listCriteria(cri);
	}

}
