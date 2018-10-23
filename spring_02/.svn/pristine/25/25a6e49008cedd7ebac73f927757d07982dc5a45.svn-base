package com.tj.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tj.dto.Member;
import com.tj.service.MemberService;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberService mService = (MemberService)context.getBean("mService"); //서비스를 빈에서 불러와 선언
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//cmd 파라미터 분리
		String cmd = request.getParameter("cmd");
		RequestDispatcher rd = null;
		//cmd 로 명령 분기
		if(cmd.equals("join")) {
			//회원가입 파라미터 분리
			String memberId = request.getParameter("memberId");
			String memberPw = request.getParameter("memberPw");
			String memberName = request.getParameter("memberName");
			String memberNick = request.getParameter("memberNick");
			String email = request.getParameter("email");
			String birthDate = request.getParameter("birthDate");
			//DTO에 넣어준다
			Member m = new Member ();
			m.setMemberId(memberId);
			m.setMemberPw(memberPw);
			m.setMemberName(memberName);
			m.setMemberNick(memberNick);
			m.setEmail(email);
			m.setBirthDate(birthDate);
			//DB에 통신
			int resultCnt = mService.join(m);
			rd = request.getRequestDispatcher("/login.jsp");
			request.setAttribute("msg", (resultCnt == 1)? "회원가입 완료" : "회원가입 실패");
			rd.forward(request, response);
		} 
		else if(cmd.equals("login")) {
			String memberId = request.getParameter("memberId");
			String memberPw = request.getParameter("memberPw");
			int resultCnt = mService.login(memberId, memberPw); 
			switch(resultCnt) {
			case 1:
				rd = request.getRequestDispatcher("/home.jsp");
				break;
			case 2: case 3:	
				rd = request.getRequestDispatcher("/login.jsp");
				request.setAttribute("msg", (resultCnt == 2)? "비밀번호가 올바르지 않습니다" : "회원 ID를 찾을 수 없습니다");
			}
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
