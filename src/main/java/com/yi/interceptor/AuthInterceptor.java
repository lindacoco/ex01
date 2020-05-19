package com.yi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) //핸들러안에 모델이들어감
			throws Exception {
		//auth키가 있으면 
		HttpSession session = request.getSession(); //리퀘스트를 통해 session끄집어냄 이 안에서 다 해결해야함
		Object object = session.getAttribute("Auth");
		if(object == null) { //로그인을 하지 않은 경우 
			//로그인 가도록  //ex01/member/loginForm 원래는 redirect:/썼지만 여기선 이안에서 다 해결해야함 
			response.sendRedirect(request.getContextPath()+"/member/loginForm"); //pageContext는 자바 스크립트
			
			//제어는 servlet! 
			return false; //기존 컨트롤러 진입을 막게 됨 등록을 요청했으면 등록 못하도록 
		}

		return true;
	}
	
}
