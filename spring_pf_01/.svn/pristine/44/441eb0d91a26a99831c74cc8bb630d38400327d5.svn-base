package com.yhs.pf.service.impl;

import java.util.HashMap;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhs.pf.dao.MemberDao;
import com.yhs.pf.dto.Member;
import com.yhs.pf.exception.MemberNotFoundException;
import com.yhs.pf.exception.PasswordMissmatchException;
import com.yhs.pf.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired private MemberDao mDao;

	@Override
	public int join(HashMap<String, Object> memberInfo) {
		return mDao.join(memberInfo);
	}

	@Override
	public Member login(String memberId, String memberPw) throws Exception {
		Member member = mDao.findMember(memberId);
		if(member != null) {
			if(member.getMemberPw().equals(mDao.makeCipherText(memberPw))) {
				return member;
			} else {
				throw new PasswordMissmatchException(); // DB에 저장된 암호와 입력받은 암호가 일치하지 않는 경우
			}
		} else {
			throw new MemberNotFoundException(); // 해당 아이디로 DB에서 ID를 검색하지 못하였을 경우
		}
	}

	@Override
	public int checkId(HashMap<String, String> params) {
		int result = mDao.checkId(params);
		return result;
	}

	@Override
	public ArrayList<HashMap<String, Object>> mList(HashMap<String, String> params) {
		return mDao.mList(params);
	}

	@Override
	public int mListCount(HashMap<String, String> params) {
		return mDao.mListCount(params);
	}

	@Override
	public int delMember(HashMap<String, String> params) {
		return mDao.delMember(params);
	}
}