package com.tj.service;

import java.util.HashMap;

public interface MemberService {
	/**
	 * 입력받은 회원정보를 맵으로 받아 회원 가입 처리를 함. 같은 ID가 있을 경우 회원가입 실패 메시지 표시.
	 * @param params
	 * @return 회원가입 성공 여부 값(int).  1:회원가입 성공 0:중복 ID 혹은 회원가입 실패
	 */
	public int join(HashMap<String,String> params);
	/**
	 * 회원 ID와 비밀번호를 입력받아 ID를 조회한 후 비밀번호를 매칭하여 로그인 처리를 함.
	 * @param memberId
	 * @param memberPw
	 * @return 로그인 성공 여부 값(int). 1:로그인 성공 2:비밀번호 불일치 3:ID 불일치
	 */
	public int login(String memberId, String memberPw);
}
