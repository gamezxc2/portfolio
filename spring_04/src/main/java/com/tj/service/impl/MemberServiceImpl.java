package com.tj.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tj.dao.MemberDao;
import com.tj.dto.Member;
import com.tj.service.MemberService;

@Service 
public class MemberServiceImpl implements MemberService {
	@Autowired private MemberDao mDao;
	/*
	public void setMemberDao(MemberDao mDao) {
		this.mDao = mDao;
	}*/
	
	@Override
	public int join(HashMap<String, String> params) {
		List<Member> members = mDao.findMember(params.get("memberId"));
		return (members.size() == 0)? mDao.join(params) : 0;
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