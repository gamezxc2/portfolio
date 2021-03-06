package com.tj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface BoardService {
	/**
	 * 게시글 쓰기
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
	 * 총 게시글 갯수 불러오기
	 * @return
	 */
	public int getTotalCount(HashMap<String, String> params);
	
	public Map<String, Object> getBoard(int typeSeq, int boardSeq);
	
	public int deleteBoard(int typeSeq, int boardSeq);
	
	public int updateBoard(HashMap<String,String> params);
}
