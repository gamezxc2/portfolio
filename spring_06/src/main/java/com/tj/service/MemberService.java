package com.tj.service;

import java.util.HashMap;

import com.tj.dto.Member;

public interface MemberService {
	/**
	 * 입력받은 정보를 DB에 입력하여 등록
	 * @param memberInfo
	 * @return mDao.join();
	 */
	public int join(Member m);
	/**
	 * 입력받은 정보와 DB에 등록된 정보를 조회하여 로그인 여부를 확인하는 메서드
	 * @param memberId
	 * @param memberPw
	 * @return Member 가입한 사용자에 대한 정보
	 * @throws Exception 
	 */
	public Member login(String memberId, String memberPw) throws Exception;
	
	/**
	 * 전달 받은 파라미터로 ID가 중복되는지 체크하는 메서드
	 * @param params 가입하려는 사용자가 입력한 정보
	 * @return
	 */
	public int checkId(HashMap<String, String> params);
}
