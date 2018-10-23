package com.tj.service.impl;

import com.tj.dao.MemberDao;
import com.tj.dto.Member;
import com.tj.service.MemberService;

public class MemberServiceImpl implements MemberService {
	private MemberDao mDao;
	public void setMemberDao (MemberDao mDao) {
		this.mDao = mDao;
	}
	@Override
	public int join(Member m) {
		mDao.join(m);
		return 0;
	}
	
	@Override
	public Member login(String memberId, String memberPw) {
		// TODO Auto-generated method stub
		return null;
	}

}
