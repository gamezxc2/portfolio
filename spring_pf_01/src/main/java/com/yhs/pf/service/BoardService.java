package com.yhs.pf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardService {
	/**
	 * 게시글 DB에 등록
	 * @param params 게시판 번호, 사용자ID, 닉네임, 제목, 내용, 첨부파일여부
	 * @return 작성이 완료 되었을 경우 기대값 1
	 */
	public int write(HashMap<String, Object> params);
	/**
	 * 모든 게시글 불러오기
	 * @param params 게시판 번호, 검색 옵션, 검색어
	 * @return 해당하는 조건의 모든 게시글을 가지는 List
	 */
	public List<Map<String,Object>> selectList(HashMap<String, String> params); 
	/**
	 * 총 게시글 수 가져오기
	 * @param params 게시판 번호, 검색 옵션, 검색어
	 * @return 총 게시글 수(int)
	 */
	public int getTotalCount(HashMap<String, String> params);
	
	/**
	 * 조회할 글 불러오기
	 * @param typeSeq 게시판 번호
	 * @param boardSeq 게시글 번호
	 * @return 해당 조건에 만족하는 게시글을 반환
	 */
	public Map<String, Object> getBoard(int typeSeq, int boardSeq);
	
	/**
	 * 해당 게시글 삭제하기
	 * @param typeSeq 게시판 번호
	 * @param boardSeq 게시글 번호
	 * @return 삭제에 성공하였을 경우 1, 실패하였을 경우 1 이외의 값.
	 */
	public int deleteBoard(int typeSeq, int boardSeq);
	
	/**
	 * 해당 게시글의 내용 변경하기.
	 * @param params 변경할 글 제목, 변경할 글 내용,해당 게시판 번호, 해당 게시글 번호
	 * @return 수정에 성공하였을 경우 기대값 1
	 */
	public int updateBoard(HashMap<String,String> params);
	
	/**
	 * 게시글 추천하기
	 * @param params 해당 게시판 번호, 해당 게시글 번호, 사용자 ID
	 * @return 추천 결과에 따른 안내 메시지
	 */
	public String recommend(HashMap<String,Object> params);
	
}
