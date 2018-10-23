package com.tj.controller;

import java.io.IOException;
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
 * Servlet implementation class UserController
 */
@WebServlet("/UserController.do")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
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
		//cmd 파라미터 분리
		String cmd = request.getParameter("cmd");				
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
			mService.join(m);
		} 
		else if(cmd.equals("login")) {
			
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
