package com.tj.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
	
	@RequestMapping("/member/join.do")
	public void join(@RequestParam HashMap<String, String> params) {
		System.out.println("/member/join.do called!!");
		System.out.println("params = "+params);
	}
	
	@RequestMapping("/member/login.do")
	public void login() {
		System.out.println("/member/login.do called!!");
	}
}
