package com.yhs.pf.controller;

import java.util.HashMap;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.yhs.pf.dto.Member;
import com.yhs.pf.exception.MemberNotFoundException;
import com.yhs.pf.exception.PasswordMissmatchException;
import com.yhs.pf.service.MemberService;

@Controller
public class MemberController {
	Logger logger = Logger.getLogger(MemberController.class);
	// Service DI 구현 코드
	@Autowired private MemberService mService;
	
	@Value("#{config['project.context.path']}")
	private String contextRoot;
	
	/**
	 * 입력받은 ID와 같은 ID가 있는지 조회하는 메서드
	 * @param params 사용자 ID
	 * @return 같은 ID가 없을 경우 기대값 0
	 */
	@RequestMapping("/member/checkId.do")
	@ResponseBody
	public int checkId(@RequestParam HashMap<String, String> params) {
		logger.debug("/member/checkId.do params = " + params);
		int cnt = mService.checkId(params); //checkId로 중복된 ID개수를 반환받아 입력, 그 결과값을 반환함.
		return cnt;
	}
	
	/**
	 * 로그인 및 회원가입 페이지로 이동.
	 * @param session 로그인 정보를 가지고 있는 세션
	 * @return login.jsp 로 이동. 로그인 상태라면 index.do
	 */
	@RequestMapping("/member/goLoginPage.do")
	public ModelAndView goLoginPage(@RequestParam HashMap<String, Object> params,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("memberId") != null) { //로그인 되어있는 상태라면
			RedirectView rv = new RedirectView(contextRoot+"/index.do"); //초기화면으로 이동하도록 
			mv.setView(rv);
		} else {
			mv.setViewName("login"); //로그인 되어있지 않은 경우에만 login.jsp로 이동
			mv.addObject("msg", params.get("msg"));
			mv.addObject("memberId",params.get("memberId"));
			mv.addObject("memberPw",params.get("memberPw"));
		}
		return mv;
	}
	
	/**
	 * 회원 가입 메서드
	 * @param memberInfo 사용할 ID, 사용할 비밀번호, 사용자 이름, 닉네임, 이메일, 생년월일
	 * @return ModelAndView 회원가입 성공 여부 메시지와 함께 login.jsp로 이동
	 */
	@RequestMapping("/member/join.do")
	public ModelAndView join(@RequestParam HashMap<String, Object> memberInfo) {
		String msg = (mService.join(memberInfo) == 1) ? "회원가입 성공!!" : "회원가입 실패!!";
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login"); // 회원 가입 결과에 상관없이 이동할 페이지는 login.jsp
		mv.addObject("msg", msg); // 회원가입 성공 여부에 따라 표시해줄 메시지를 반송
		
		return mv;
	}
	
	/**
	 * ID와 비밀번호를 입력받아 해당 정보를 조회하여 일치할 경우 홈페이지로, 다른 경우 로그인 페이지로
	 * @param memberId 입력받은 회원 ID
	 * @param memberPw 입력받은 회원 비밀번호
	 * @return ModelAndView 로그인 성공시 index.do 실패시 goLoginPage.do
	 */
	@RequestMapping("/member/login.do")
	public ModelAndView login(String memberId, String memberPw, HttpSession session) {
		ModelAndView mv = new ModelAndView(); 
		String page = "/member/goLoginPage.do";
		String msg = null;
		Member member = null;
		try {
			member = mService.login(memberId, memberPw); 
			session.setAttribute("memberId", member.getMemberId()); //세션에 가지고 있을 정보들을 입력
			session.setAttribute("memberIdx", member.getMemberIdx()); 
			session.setAttribute("memberName", member.getMemberName());
			session.setAttribute("memberNick", member.getMemberNick());
			session.setAttribute("typeSeq", member.getTypeSeq());
			page = "/index.do"; //try 중 예외 발생시 index.do가 입력되지 않고 예외 처리
		} catch (PasswordMissmatchException pme) {
			msg = pme.getMessage(); // 비밀번호 오류 메시지를 저장하고 있는 사용자 지정 예외에서 메시지를 불러옴 
		} catch (MemberNotFoundException mnfe) {
			msg = mnfe.getMessage(); // 사용자 오류 메시지를 저장하고 있는 사용자 지정 예외에서 메시지를 불러옴
		} catch (Exception e) {
			e.printStackTrace(); // 그 이외의 예외 발생시
		}
		mv.addObject("msg", msg);
		RedirectView rv = new RedirectView(contextRoot+page);
		mv.setView(rv);
		return mv;
	}
	/**
	 * 로그인 정보를 가지는 세션을 초기화 하는 메서드
	 * @param session 로그인 정보를 가지고 있는 세션
	 * @return ModelAndView index.do 실행
	 */
	@RequestMapping("/member/logout.do")
	public ModelAndView logout(HttpSession session) {
		session.invalidate(); // 세션 초기화
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView(contextRoot+"/index.do"); //초기화면으로 이동.
		mv.setView(rv);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/member/getListData.do")
	public HashMap<String,Object> getListData(@RequestParam HashMap<String, String> params) {
		logger.debug("/member/getListData.do params = "+params);
		//한 페이지에 보여줄 게시글 수
		int rows = Integer.parseInt(params.get("rows"));
		//현재 페이지
		int currentPage = Integer.parseInt(params.get("page"));
		//전체 회원 수 구하기
		int totalMember = mService.mListCount(params);
		//총 페이지 수
		int totalPage = (int)(Math.ceil((double) totalMember / rows));
		//시작 인덱스  : 오라클에서는 끝 인덱스도 필요.
		int startIdx = (currentPage-1) * rows; //현재 페이지에서 보여줄 첫 글 번호
		params.put("startIdx", String.valueOf(startIdx));
		ArrayList<HashMap<String,Object>> list = mService.mList(params);
		logger.debug("/member/getListData.do params = "+ list);
		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put("page", params.get("page")); // 현재 페이지
		result.put("total", totalPage); // 총 페이지 수
		result.put("rows", list); // 데이터 목록
		result.put("records", totalMember); //총 회원 수
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/member/delMember.do")
	public HashMap<String,String> delMember(@RequestParam HashMap<String, String> params) {
		logger.debug("/member/delMember.do params = "+ params);
		HashMap<String, String> map = new HashMap<String,String>();
		int cnt = mService.delMember(params);
		map.put("msg", (cnt==1)?"삭제되었습니다.":"삭제 실패!");
		map.put("result",String.valueOf(cnt));
		return map;
	}
	
}
