package com.tj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tj.service.BoardService;

@Controller
public class BoardController {
	private Logger logger = Logger.getLogger(BoardController.class);
	@Autowired private BoardService bService;
	
	/**
	 * 게시글 목록 표시 
	 * @param 필요한 파라미터가 없을 경우 자동적으로 초기화 하도록 3항 연산자를 사용.
	 * @return /board/list.jsp로 이동
	 */
	@RequestMapping("/board/list.do")
	public ModelAndView list(@RequestParam HashMap<String, String> params) {
		logger.debug("/board/list.do params -  " + params); //로그에 각 게시판 항목 표시
		logger.debug("selectType = " +params.get("selectType"));
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/list"); // list.jsp 지정 (spring_05/pages/  .jsp를 servlet.xml 에서 지정)
		if(params.containsKey("msg")) mv.addObject("msg",params.get("msg")); //msg가 존재하는 경우 list.do로 msg를 넘겨줌
		//페이징 처리 1. 현재 페이지, 보여줄 게시글 수
		//containsKey : 해당 키가 값을 가지고 있을 경우 true , 값이 없을 경우 false로 나옴
		int currentPage = params.containsKey("currentPage")? Integer.parseInt(params.get("currentPage")) : 1;
		int pageArticleSize = params.containsKey("pageArticleSize")?Integer.parseInt(params.get("pageArticleSize")):10;
		int totalArticleCnt = bService.getTotalCount(params); // 총 게시글의 숫자 
		int totalPageCnt = (int)Math.ceil((double) totalArticleCnt  / pageArticleSize) ; //총게시글수와 페이지당보여줄게시글수로 총 페이지수 계산
		int startIdx = ((currentPage-1) * pageArticleSize); //현재 페이지에서 보여줄 첫 글 번호
		int endIdx = (currentPage * pageArticleSize) -1; // 현재 페이지에서 보여 줄 마지막 게시글 번호
		endIdx = (totalArticleCnt > endIdx)? endIdx : totalArticleCnt; //총게시글수가 마지막 번호보다 클 경우 표시하지 않음.
		int pageBlockSize = 10;
		int pageBlockStart = (((currentPage-1)/pageBlockSize)*pageBlockSize) +1 ; //페이지 블록의 시작점
		int pageBlockEnd = (((currentPage-1)/pageBlockSize)*pageBlockSize) +pageBlockSize ; //페이지 블록의 마지막점
		pageBlockEnd = (totalPageCnt > pageBlockEnd)? pageBlockEnd : totalPageCnt; //총 페이지 수가 페이지블록의 끝보다 크다면 총 페이지 수로 나오도록
		
		
		// 게시글 전체를 가져온다.
		params.put("startIdx", String.valueOf(startIdx)); // 파라미터를 가지는 맵에 시작번호를 입력
		params.put("pageArticleSize", String.valueOf(pageArticleSize)); // 파라미터를 가지는 맵에 1페이지당 보여줄 게시글 수 입력
		List<Map<String,Object>> board = bService.selectList(params); //게시글을 불러오는 Service 메서드를 호출하여 결과를 List<Map>으로 받음
		mv.addObject("selectType", params.get("selectType") );
		mv.addObject("searchText", params.get("searchText") );
		mv.addObject("list",board);
		mv.addObject("currentPage",currentPage);
		mv.addObject("pageBlockSize",pageBlockSize);
		mv.addObject("pageBlockStart",pageBlockStart);
		mv.addObject("pageBlockEnd",pageBlockEnd);
		mv.addObject("totalPageCnt", totalPageCnt); //List를 표시하기 위한 파라미터를 addObject로 입력하여 jsp로 보내기
		return mv;
	}
	
	/**
	 * 글쓰기 페이지로 이동
	 * @return pages/board/write.jsp
	 */
	@RequestMapping("/board/goWritePage.do")
	public ModelAndView goWritePage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/write");
		return mv;
	}
	
	/**
	 * 게시글 작성
	 * @param params
	 * @return 글 작성 완료시 게시판 화면으로 오류시 글 작성 화면으로 이동.
	 */
	@RequestMapping("/board/write.do")
	public ModelAndView write(@RequestParam HashMap<String, String> params) {
		logger.debug("/board/write.do params -  " + params);
		/*String contents = params.get("contents");
		contents = contents.replaceAll("\\r", "<br>"); Editor를 이용하지 않을 경우 Enter키를 줄바꿈으로 변환해줄 코드가 필요.
		params.put("contents", contents);*/
		ModelAndView mv = new ModelAndView();
		// 받은 파라미터를 Service로 넘겨주어 올바르게 입력시 list.do, 오류 발생시 write.do 로 이동.
		RedirectView rv = new RedirectView("/spring_05"+(bService.write(params) == 1 ? "/board/list.do" : "/board/write.do"));
		mv.setView(rv);
		return mv;
	}
	
	/**
	 * 게시글 읽기
	 * @param params
	 * @return 게시판 항목을 담은 MAP과 msg를 넘겨받았을 경우에만 msg 문자열을 jsp로 출력. 
	 */
	@RequestMapping("/board/read.do")
	public ModelAndView read(@RequestParam HashMap<String,String> params){
		logger.debug("/board/read.do params " + params);
		ModelAndView mv = new ModelAndView();
		Map<String, Object> board = bService.getBoard(Integer.parseInt(params.get("typeSeq")), Integer.parseInt(params.get("boardSeq")));
		String msg = params.get("msg");
		if(msg != null) {
			mv.addObject("msg",msg);
		}
		mv.setViewName("board/read");
		mv.addObject("board", board);
		return mv;
	}
	
	/**
	 * 게시글 삭제 메서드.
	 * @param params
	 * @param session 로그인 확인.
	 * @return 삭제 성공시 list / 삭제 실패시 read / 세션 만료시 index
	 */
	@RequestMapping("/board/delete.do")
	public ModelAndView delete(@RequestParam HashMap<String,String> params, HttpSession session) {
		logger.debug("/board/delete.do params = " + params); //log 출력
		logger.debug("/board/delete.do params session = " + session.getAttribute("memberId"));
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("memberId") != null) { //로그인이 유효하다면.
			RedirectView rv;
			//서비스에서 게시 글 삭제 메서드를 호출하여 그 결과가 1이라면 
			if(bService.deleteBoard(Integer.parseInt(params.get("typeSeq")), Integer.parseInt(params.get("boardSeq"))) == 1) {
				rv = new RedirectView("/spring_05//board/list.do"); //리스트로 이동
				mv.addObject("msg","해당 게시글이 삭제되었습니다."); // 삭제 성공하였음을 표시할 메시지 전달
				mv.setView(rv);
			} else {
				String url = "/board/read.do?boardSeq=";
				url = url.concat(Integer.parseInt(params.get("boardSeq"))+"&typeSeq="+Integer.parseInt(params.get("typeSeq")));
				rv = new RedirectView(url); //해당 게시글로 다시 돌아가도록 페이지 지정
				mv.addObject("msg","삭제 실패! 관리자에게 문의하세요"); // 삭제 실패하였음을 표시할 메세지 전달
				mv.setView(rv);
			}
		} else { // 로그인이 안된 상태
			mv.setViewName("common/error"); // 세션 만료로 인한 로그인에러
			mv.addObject("msg","로그인 해주시기 바랍니다."); // 에러 메시지 전달
			mv.addObject("nextLocation", "/index.do"); //다음 위치 지정.
		}
		return mv;
	}
	
	/**
	 * 게시글 수정 페이지 이동 메서드.
	 * @param params
	 * @param session
	 * @return 해당 게시글의 정보를 보드에 담아 update.jsp로 전송하여 표시해주도록 함. 세션 만료시 index.jsp로 이동.
	 */
	@RequestMapping("/board/goUpdatePage.do")
	public ModelAndView goUpdatePage(@RequestParam HashMap<String,String> params, HttpSession session) {
		logger.debug("/board/goUpdatePage.do params = " + params);
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("memberId") != null) { 
			mv.setViewName("board/update"); //update.jsp로 이동
			// 수정한 글 내용을 표시해주기 위하여 게시글 불러오기.
			Map<String, Object> board = bService.getBoard(Integer.parseInt(params.get("typeSeq")), Integer.parseInt(params.get("boardSeq")));
			mv.addObject("board", board);
		} else { //로그인 세션이 만료되었을 경우 error페이지 지정.
			mv.setViewName("common/error");
			mv.addObject("msg","로그인 하세요.");
			mv.addObject("nextLocation", "/index.do");
		}
		return mv;
	}
	
	
	/**
	 * 게시글 수정 메서드
	 * @param params 수정한 글 내용을 저장하여 받아온다
	 * @param session 로그인 상태 확인을 위한 세션
	 * @return 서비스에서 글 수정 메서드를 호출하여 그 결과값이 1일 경우 해당 게시글 읽기로, 아닐 경우 게시글 목록으로, 세션 만료시 초기화면으로
	 */
	@RequestMapping("board/update.do")
	public ModelAndView update(@RequestParam HashMap<String,String> params, HttpSession session) {
		logger.debug("/board/update.do params = " + params);
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("memberId") != null) {
			RedirectView rv;
			if(bService.updateBoard(params)  == 1) { //update 실행한 결과 횟수가 반환되어 옴.기대값 1
				String url = "/spring_05//board/read.do?boardSeq=";
				url += Integer.parseInt(params.get("boardSeq")) + "&typeSeq=" + Integer.parseInt(params.get("typeSeq"));
				logger.debug("update.do url params" + url);
				rv = new RedirectView(url); 
				mv.addObject("msg","수정 완료"); //update 성공 메시지 전달.
				mv.setView(rv);
			} else {
				rv = new RedirectView("/spring_05//board/list.do"); //글 수정 실패시 게시글 목록으로 이동
				mv.addObject("msg","글 수정에 실패하였습니다."); //update 실패 메시지 전송
				mv.setView(rv);
			}
		} else { //로그인 세션이 만료되었을 경우 error페이지 지정.
			mv.setViewName("common/error"); 
			mv.addObject("msg","다시 로그인 하세요.");
			mv.addObject("nextLocation", "/index.do");
		}
		return mv;
	}
}
