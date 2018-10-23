package com.tj.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.tj.service.MemberService;

@Controller
public class MemberController {
	@Autowired //Service DI 구현 코드
	private MemberService mService;
	/**
	 * 입력받은 파라미터를 Service로 전송하여 회원가입 처리를 하여 가입 여부를 확인. 
	 * @param params
	 * @return (ModelAndView) 회원가입 성공 여부 메시지
	 */
	@RequestMapping("/member/join.do")
	public ModelAndView join(@RequestParam HashMap<String, String> params) {
		System.out.println("/member/join.do called!!");
		System.out.println("params = "+params);
		int resultCnt = mService.join(params);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/login.jsp"); // request.getRequestDispatcher("login.jsp")
		mv.addObject("msg",(resultCnt==1)?"회원가입 성공":"회원가입 실패"); // request.setAttribute("msg","메시지");
 		return mv; // rd.forward(request,response);
	}
	/**
	 * 입력받은 회원ID와 비밀번호를 Service로 전송하여 로그인 처리.
	 * @param params
	 * @return (ModelAndView) 로그인 성공시 홈페이지 이동, 실패시 실패 메시지 표시.
	 */
	@RequestMapping("/member/login.do")
	public ModelAndView login(@RequestParam HashMap<String, String> params) {
		System.out.println("/member/login.do called!!");
		int resultCnt = mService.login(params.get("memberId"), params.get("memberPw")); 
		ModelAndView mv = new ModelAndView();
		switch(resultCnt) {
		case 1:
			mv.setViewName("/home.jsp");
			break;
		case 2: case 3:	
			mv.setViewName("/login.jsp");
			mv.addObject("msg",(resultCnt==2)?"비밀번호가 올바르지 않습니다" : "회원 ID를 찾을 수 없습니다");
		}
		return mv;
	}
}
