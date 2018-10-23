package com.tj.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.tj.dao.MemberDao;
import com.tj.dto.Member;

public class MemberDaoImpl extends JdbcDaoSupport implements MemberDao{

	@Override
	public int join(Member m) {
		getJdbcTemplate();
		return 0;
	}

	@Override
	public Member login(String memberId, String memberPw) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
