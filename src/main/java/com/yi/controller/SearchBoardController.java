package com.yi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yi.domain.BoardVO;
import com.yi.domain.PageMaker;
import com.yi.domain.SearchCriteria;
import com.yi.service.BoardService;
import com.yi.util.UploadFileUtils;

@Controller
@RequestMapping("/sboard/*") //command에 항상 /sboard/로 시작한다
public class SearchBoardController {
	
	@Resource(name ="uploadPath") //서블릿컨텍스트의 id값과 일치해야함 
	private String uploadPath;

	@Autowired
	BoardService service;
	
	@RequestMapping(value = "listPage", method = RequestMethod.GET)
	public String listPage(SearchCriteria cri, Model model) throws Exception {
	 //	System.out.println("------------------------"+cri);
		List<BoardVO> list = service.listSearchCriteria(cri);
		
		
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.totalSearchCount(cri));
		
		model.addAttribute("cri", cri);
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", pageMaker);
	
		return "/sboard/listPage";
	}
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String register() {
		return "/sboard/register";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(BoardVO vo, List<MultipartFile> imgfiles) throws Exception {
		//자동으로 set함수로 주입이 된다
		//System.out.println("resiger POST"+vo);
	
		System.out.println(vo);
		
		
		ArrayList<String> fullName = new ArrayList<String>();
		for(MultipartFile file : imgfiles) {
			if(file.getSize() == 0) {
				break;
			}
			//System.out.println(file.getOriginalFilename());
			//System.out.println(file.getSize());
			
			//업로드 처리하기
			String savedName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		   //얘가 썸네일 던져줌 
			fullName.add(savedName);
		}
		vo.setFiles(fullName);
		service.create(vo);
		
		return "redirect:/sboard/listPage";
	}
	@RequestMapping(value = "readPage", method = RequestMethod.GET)
	public String readPage(int bno, SearchCriteria cri, Model model) throws Exception{  //넘어오는게 하나라서 이렇게 받음 많으면 VO로 
		BoardVO vo = service.readAttachByBno(bno);
        vo.setViewcnt(vo.getViewcnt()+1);
		service.update(vo);
		//System.out.println(vo.getViewcnt());
		model.addAttribute("board",vo);
	    model.addAttribute("cri",cri);
		return "/sboard/readPage";
	}
	@RequestMapping(value = "updatePage", method = RequestMethod.GET)
	public String update(int bno,SearchCriteria cri, Model model) throws Exception{
		BoardVO vo = service.readAttachByBno(bno);
		model.addAttribute("board",vo);
		//System.out.println(vo);
		model.addAttribute("cri",cri);
		return "/sboard/updatePage";
	}
	@RequestMapping(value = "updatePage", method = RequestMethod.POST)
	public String update(BoardVO vo,SearchCriteria cri,Model model, String[] delfiles, List<MultipartFile> imgfiles) throws Exception{
	  
	   
	   ArrayList<String> fullName = new ArrayList<String>();
	   
	   //새로 넣는 파일 
	   if(imgfiles.get(0).getBytes().length != 0 ) {
		for(MultipartFile file : imgfiles) {
			
			//업로드 처리하기
			String savedName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		   //얘가 썸네일 던져줌 
			fullName.add(savedName);
			
		}
		 vo.setFiles(fullName);
		 service.updateAndAddAttach(vo);
	   }
		
		
		
	  //삭제하는 아이 	
	   ArrayList<String> fullNames = new ArrayList<String>();
	   
	  if(delfiles != null ) {
		  
	   for(String delfile : delfiles) {
		   //System.out.println(delfile);
		   fullNames.add(delfile);
		   service.delPic(delfile);
		 
		   //썸네일 지우기
		   File file = new File(uploadPath+delfile);
		   file.delete();
		   
		   String originalName = delfile.substring(0,12)+delfile.substring(14);
	//	   System.out.println("오리지날네임"+originalName);
		   File originFile = new File(uploadPath+originalName);
		   originFile.delete();
	     }
	   	 vo.setFiles(fullNames);
		 service.updateAndDeleteAttach(vo);
	  }
	  
	   if(delfiles == null && imgfiles.get(0).getBytes().length == 0 ) {
		   service.update(vo);
	   }
	   
	    vo.setViewcnt(vo.getViewcnt()-1);
	    
	    model.addAttribute("page", cri.getPage());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
	   return "redirect:/sboard/readPage?bno="+vo.getBno()+"&page="+cri.getPage();
	}
	@RequestMapping(value = "removePage", method = RequestMethod.GET)
	public String removePage(int bno, SearchCriteria cri, Model model) throws Exception{
		BoardVO vo = service.readAttachByBno(bno);
		//얘가 가진 사진들을 폴더에서 삭제한다 
	    for(String delfiles : vo.getFiles()) {
	    	File file = new File(uploadPath+delfiles);
			   file.delete();
			   
			   String originalName = delfiles.substring(0,12)+delfiles.substring(14);
			   File originFile = new File(uploadPath+originalName);
			   originFile.delete();
	    }
		service.deleteWithAttach(bno);
		
		model.addAttribute("page", cri.getPage());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
		return "redirect:/sboard/listPage?page="+cri.getPage();
	}
	
	// c드라이브에 있는 이미지에 대한 데이터를 직접 가져와야한다. ajax용으로 처리됨
	@ResponseBody
	@RequestMapping(value="displayFile", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String filename){
		ResponseEntity<byte[]> entity = null;
		
		//System.out.println("displayFile-----------"+ filename);
		InputStream in = null;
		try {
			in = new FileInputStream(uploadPath+filename); //파일개체는 오류처리하라고..
			String format = filename.substring(filename.lastIndexOf(".")+1); //파일 확장자 뽑아내기 점 빼고
		     MediaType mType = null;
			
			if(format.equalsIgnoreCase("png")) {
				mType = MediaType.IMAGE_PNG;
			}else if(format.equalsIgnoreCase("jpg")|| format.equalsIgnoreCase("jpeg")) {
				mType = MediaType.IMAGE_JPEG;
			}else if(format.equalsIgnoreCase("GIF")) {
				mType = MediaType.IMAGE_GIF;
			}
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(mType);
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers ,HttpStatus.OK);
		    
			
			in.close();
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
