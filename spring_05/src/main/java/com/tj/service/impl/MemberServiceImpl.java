package com.tj.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tj.dao.MemberDao;
import com.tj.dto.Member;
import com.tj.exception.MemberNotFoundException;
import com.tj.exception.PasswordMissmatchException;
import com.tj.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired private MemberDao mDao;

	@Override
	public int join(HashMap<String, Object> memberInfo) {
		return mDao.join(memberInfo);
	}

	@Override
	public Member login(String memberId, String memberPw) throws Exception {
		Member member = mDao.findMemberId(memberId);
		if(member != null) {
			if(member.getMemberPw().equals(mDao.makeCipherText(memberPw))) {
				return member;
			} else {
				throw new PasswordMissmatchException();
			}
		} else {
			throw new MemberNotFoundException();
		}
	}

	@Override
	public int checkId(HashMap<String, String> params) {
		int result = mDao.checkId(params);
		return result;
	}

}
