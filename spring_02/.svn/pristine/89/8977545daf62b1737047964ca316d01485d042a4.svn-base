package com.tj.service.impl;

import java.util.List;

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
		List<Member> members = mDao.findMember(m.getMemberId());
		return (members.size() == 0)? mDao.join(m) : 0; 
	}
	
	@Override
	public int login(String memberId, String memberPw) {
		List<Member> members = mDao.findMember(memberId);
		int cnt = 0;
		if(members.size() == 1) {
			String pw1 = mDao.makeCipherText(memberPw);
			String pw2 = members.get(0).getMemberPw();
			cnt = (pw1.equals(pw2))? 1 : 2; // 1: 로그인 성공 2: 비밀번호 매칭 오류
		} else { //ID 매칭 실패
			cnt = 3;
		}
    	return cnt;
	}
}
