package com.tj.dao;

import java.util.HashMap;
import java.util.List;

import com.tj.dto.Member;

public interface MemberDao {
	/**
	 * 입력받은 회원 정보를 쿼리에 입력하여 DB에 등록
	 * @param params
	 * @return DB에 등록한 횟수(int)를 반환. 성공시 1.
	 */
	public int join (HashMap<String, String> params);
	/**
	 * 회원ID를 입력받아 DB와 조회하여 일치하는 ID를 List에 담아서 반환
	 * @param memberId
	 * @return 입력한 ID와 일치하는 ID를 가지는 회원정보 리스트(List)를 반환.
	 */
	public List<Member> findMember(String memberId);
	/**
	 * 비밀번호를 입력받아 암호화하여 출력
	 * @param memberPw
	 * @return 입력한 문자열을 암호화한 문자열(String)
	 */
	public String makeCipherText(String memberPw);
}
