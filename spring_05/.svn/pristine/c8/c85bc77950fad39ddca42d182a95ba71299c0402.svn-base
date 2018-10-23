package com.tj.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface BoardDao {
	/**
	 * 게시글 DB에 등록
	 * @param params
	 * @return
	 */
	public int write(HashMap<String, String> params);
	/**
	 * 모든 게시글 불러오기
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> selectList(HashMap<String, String> params);
	/**
	 * 총 게시글 수 가져오기
	 * @return 총 게시글 수(int)
	 */
	public int getTotalCount(HashMap<String, String> params);
	/**
	 * 조회 수 올리기
	 * @return
	 */
	public int updateHit(int typeSeq, int boardSeq);
	/**
	 * 조회할 글 불러오기
	 * @param typeSeq
	 * @param boardSeq
	 * @return
	 */
	public Map<String, Object> getBoard(int typeSeq, int boardSeq);
	
	public int deleteBoard(int typeSeq, int boardSeq);
	
	public int updateBoard(HashMap<String, String> params);
}
