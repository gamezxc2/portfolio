package com.tj.service.impl;

import com.tj.dao.MemberDao;
import com.tj.service.MemberService;

public class MemberServiceImpl implements MemberService{
	private MemberDao mDao;
	public void setMemberDao (MemberDao mDao) {
		this.mDao = mDao;
	}
	@Override
	public void join() {
		System.out.println("service called");
		mDao.join();
	}
	
}
