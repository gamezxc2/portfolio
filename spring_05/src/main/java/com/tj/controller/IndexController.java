package com.tj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/home.do")
	public String home() {
		return "home"; // 페이지만 보여줄 경우 String 으로 jsp주소만 지정해줄 경우 jsp출력
	}
	
	@RequestMapping("/index.do")
	public String index() {
		return "index";
	}
}
