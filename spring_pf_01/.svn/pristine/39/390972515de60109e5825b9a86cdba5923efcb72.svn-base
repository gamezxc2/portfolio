package com.yhs.pf.dao;

import java.util.HashMap;

public interface RecommendDao {
	/**
	 * 추천 테이블에 추천한 사람과 일시를 기록
	 * @param params 추천자 ID
	 * @return 입력 성공시 기대값 1
	 */
	public int insertRecmnd(HashMap<String,Object> params);
	
	/**
	 * 해당 조건으로 중복된 추천이 있는지 조회
	 * @param params 사용자 색인 번호, 게시글 번호, 게시판 번호
	 * @return 이미 추천한 사용자 일 경우 기대값 1
	 */
	public int countRecmnd(HashMap<String,Object> params);
	
	/**
	 * 해당 게시글의 추천 테이블에 기록된 모든 정보 삭제
	 * @param board_seq 게시글 번호
	 * @param type_seq 게시판 번호
	 * @return 삭제한 추천 기록 개수
	 */
	public int deleteRecmnd(int boardSeq, int typeSeq);
}
