package com.yi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yi.domain.MemberVO;
import com.yi.service.MemberService;



@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Autowired
	private MemberService service;
	
	
	@RequestMapping(value = "loginForm",method = RequestMethod.GET)
	public String loginForm() {
		return "member/loginForm";
	}
	
	
	@RequestMapping(value = "loginForm",method = RequestMethod.POST)
	public String loginFormPost(MemberVO vo, Model model, HttpSession session) throws Exception { //한방에 주입받도록 한다 어차피 userid userpass가 있으니 
		//db에 있는 회원과 일치하는지 확인 해본다
		MemberVO dbVO = service.readMember(vo.getUserid());
		
		if(dbVO == null) { // 아이디에 해당하는 멤버가 없다.
			//다시 로그인 폼으로 이동한다
		    //고객한테 알려준다 -모델추가);
			model.addAttribute("error","존재하지 않는 id 입니다");
			return "/member/loginForm";

		}else if(dbVO.getUserpw().equals(vo.getUserpw())==false) { //비밀번호가 일치하지 않음
			model.addAttribute("error","비밀번호가 일치하지 않습니다."); //5회 이상 틀리면 어쩌구 설정도 여기서 한다 -디비에 컬럼필요 
			return "/member/loginForm";
		}
		//둘 다 있다면 여기까지 온다 - 로그인을 성공했을 때 오는 화면으로 
		session.setAttribute("Auth", vo.getUserid());
		return "redirect:/sboard/listPage"; //home컨트롤러로 이동 -홈에 / 이걸로 설정해둠
	}
	
	@RequestMapping(value = "logout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
	    session.invalidate();
	    //특정 애만 날리고 싶으면 removeAttribute
		return "redirect:/";
	}
}
