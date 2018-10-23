package com.yhs.pf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	// home으로 이동
	@RequestMapping("/home.do")
	public String home() {
		return "home"; // 페이지만 보여줄 경우 String 으로 jsp주소만 지정해줄 경우 jsp출력
	}
	
	// index 초기화면으로 이동.
	@RequestMapping("/index.do")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/test.do")
	public String test() {
		return "test";
	}
	
	//개발 노트 화면으로 이동
	@RequestMapping("/devNotes.do")
	public String devNotes() {
		return "devNotes";
	}
	
	@RequestMapping("/mList.do")
	public String memberList() {
		return "mList";
	}
	
}
