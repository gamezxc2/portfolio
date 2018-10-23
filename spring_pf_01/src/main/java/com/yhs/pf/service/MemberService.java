package com.yhs.pf.service;

import java.util.HashMap;
import java.util.ArrayList;

import com.yhs.pf.dto.Member;

public interface MemberService {
	/**
	 * 입력받은 정보를 DB에 입력하여 등록
	 * @param memberInfo 
	 * @return 가입성공 시 1 , 가입 실패시 1 이외의 값.
	 */
	public int join(HashMap<String, Object> memberInfo);
	/**
	 * 입력받은 정보와 DB에 등록된 정보를 조회하여 로그인 여부를 확인하는 메서드
	 * @param memberId 입력받은 회원 ID
	 * @param memberPw 입력받은 비밀번호
	 * @return Member 가입한 사용자에 대한 정보
	 * @throws Exception 
	 */
	public Member login(String memberId, String memberPw) throws Exception;
	
	/**
	 * 전달 받은 파라미터로 ID가 중복되는지 체크하는 메서드
	 * @param params 가입하려는 사용자가 입력한 정보
	 * @return 중복된 ID의 개수가 표시.
	 */
	public int checkId(HashMap<String, String> params);
	
	/**
	 * 모든 회원들을 조회하는 메서드
	 * @return 회원 목록 List
	 */
	public ArrayList<HashMap<String,Object>> mList(HashMap<String, String> params);
	
	/**
	 * 총 회원 수를 카운트하는 메서드
	 * @param params
	 * @return
	 */
	public int mListCount(HashMap<String, String> params);
	
	/**
	 * 선택한 회원 삭제 메서드
	 * @param params 회원번호
	 * @return 삭제한 횟수
	 */
	public int delMember(HashMap<String, String> params);
}
