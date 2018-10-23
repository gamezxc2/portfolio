package com.yhs.pf.dao;

import java.util.HashMap;
import java.util.ArrayList;

import com.yhs.pf.dto.Member;

public interface MemberDao {
	/**
	 * 회원가입.
	 * @param HashMap<String, Object> memberInfo DB에 등록할 
	 * @return jdbcTemplate.update();
	 */
	public int join(HashMap<String, Object> m);
	/**
	 * 해당 ID에 매칭되는 회원 정보를 검색하여 입력
	 * @param memberId 조회할 회원 ID
	 * @return MemberDto mDto 해당하는 회원 정보를 입력할 클래스
	 */
	public Member findMember(String memberId);
	/**
	 * 입력한 문자열을 암호화 하는 메서드
	 * @param memberPw
	 * @return String jdbcTemplate.queryForObject()
	 */
	public String makeCipherText(String memberPw);
	/**
	 * 해당 ID를 검색하여 중복되는 ID가 있는지 조회
	 * @param params
	 * @return
	 */
	public int checkId(HashMap<String, String> params);
	
	/**
	 * 회원 목록 출력 메서드
	 * @return 회원들의 정보를 가지는 Map을 모두 가진 List
	 */
	public ArrayList<HashMap<String,Object>> mList(HashMap<String, String> params);
	
	/**
	 * 총 회원 수 구하기 메서드
	 * @param params
	 * @return
	 */
	public int mListCount(HashMap<String, String> params);
	
	/**
	 * 선택한 회원 삭제 메서드
	 * @param params 회원 번호
	 * @return
	 */
	public int delMember(HashMap<String, String> params);
}