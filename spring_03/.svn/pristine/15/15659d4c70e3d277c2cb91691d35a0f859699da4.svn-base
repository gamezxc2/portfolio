package com.tj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.tj.service.MemberService;

public class OldController extends AbstractController{
	private MemberService mService;
	public void setMemberService(MemberService mService) {
		this.mService = mService;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		System.out.println("HELLO");
		System.out.println(mService);
		mService.join();
		return null;
	}

}
