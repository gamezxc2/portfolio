package com.tj.dao;

import java.util.HashMap;

import com.tj.dto.Member;

public interface MemberDao {
	/**
	 * @param HashMap<String, Object> memberInfo
	 * @return jdbcTemplate.update();
	 */
	public int join(HashMap<String, Object> memberInfo);
	/**
	 * @param memberId
	 * @return MemberDto mDto || null
	 */
	public Member findMemberId(String memberId);
	/**
	 * @param memberPw
	 * @return String jdbcTemplate.queryForObject()
	 */
	public String makeCipherText(String memberPw);
	
	public int checkId(HashMap<String,String> params);
}
