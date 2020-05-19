package com.yi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yi.domain.BoardVO;
import com.yi.domain.Criteria;
import com.yi.domain.PageMaker;
import com.yi.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService service;
	
	
	@RequestMapping(value = "/board/register",method = RequestMethod.GET)
	public String register() {
		return "/board/register";
	}
	
	@RequestMapping(value = "/board/register", method = RequestMethod.POST)
	public String register(BoardVO vo) throws Exception {
		//자동으로 set함수로 주입이 된다
		System.out.println("resiger POST"+vo);
		
		service.create(vo);
		return "redirect:/board/list";
		
	}
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception{
		List<BoardVO> list = service.list();
		model.addAttribute("list",list);
		return "/board/list";
	}
	@RequestMapping(value = "/board/read", method = RequestMethod.GET)
	public String read(int bno, Model model) throws Exception{  //넘어오는게 하나라서 이렇게 받음 많으면 VO로 
		BoardVO vo = service.readByNo(bno);
		vo.setViewcnt(vo.getViewcnt()+1);
		service.update(vo);
		//System.out.println(vo.getViewcnt());
		model.addAttribute("board",vo);
	
		return "/board/read";
		
	}
	
	@RequestMapping(value = "/board/update", method = RequestMethod.GET)
	public String update(int bno, Model model) throws Exception{
		BoardVO vo = service.readByNo(bno);
		model.addAttribute("board",vo);
		//System.out.println(vo);
		return "/board/update";
	}

	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public String update(BoardVO vo) throws Exception{
	   vo.setViewcnt(vo.getViewcnt()-1);
	  // System.out.println(vo);
	   service.update(vo);
	  // System.out.println(vo);

		return "redirect:/board/read?bno="+vo.getBno();
		
	}
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public String delete(int bno) throws Exception{
		service.delete(bno);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/board/listPage", method = RequestMethod.GET)
	public String listPage(Criteria cri, Model model) throws Exception {
		List<BoardVO> list = service.listCriteria(cri);
		
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.totalCount());
		
		
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/board/listPage";
	}
	
	
	@RequestMapping(value = "/board/readPage", method = RequestMethod.GET)
	public String readPage(int bno, Criteria cri, Model model) throws Exception{  //넘어오는게 하나라서 이렇게 받음 많으면 VO로 
		BoardVO vo = service.readByNo(bno);
		vo.setViewcnt(vo.getViewcnt()+1);
		service.update(vo);
		//System.out.println(vo.getViewcnt());
		model.addAttribute("board",vo);
	    model.addAttribute("cri",cri);
		return "/board/readPage";
		
	}
	
	@RequestMapping(value = "/board/removePage", method = RequestMethod.GET)
	public String removePage(int bno, Criteria cri, Model model) throws Exception{
		service.delete(bno);
		return "redirect:/board/listPage?page="+cri.getPage();
	}
	
	
	@RequestMapping(value = "/board/updatePage", method = RequestMethod.GET)
	public String update(int bno,Criteria cri, Model model) throws Exception{
		BoardVO vo = service.readByNo(bno);
		model.addAttribute("board",vo);
		//System.out.println(vo);
		model.addAttribute("cri",cri);
		return "/board/updatePage";
	}

	@RequestMapping(value = "/board/updatePage", method = RequestMethod.POST)
	public String update(BoardVO vo,Criteria cri,Model model) throws Exception{
	   vo.setViewcnt(vo.getViewcnt()-1);
	   service.update(vo);
	   return "redirect:/board/readPage?bno="+vo.getBno()+"&page="+cri.getPage();
		
	}
	
}
