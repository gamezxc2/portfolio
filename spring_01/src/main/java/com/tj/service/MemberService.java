package com.tj.service;
import com.tj.dto.Member;

public interface MemberService {
	public int join(Member m);
	public Member login(String memberId, String memberPw);
}
