package com.tj.dao;

import java.util.List;

import com.tj.dto.Member;

public interface MemberDao {
	public int join (Member m);
	public List<Member> findMember(String memberId);
	public String makeCipherText(String memberPw);
}
