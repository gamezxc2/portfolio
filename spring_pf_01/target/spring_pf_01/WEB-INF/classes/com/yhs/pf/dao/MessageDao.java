package com.yhs.pf.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface MessageDao {
	/**
	 * 총 메시지 개수 카운팅
	 * @return DB에 등록된 모든 메시지 개수
	 */
	public int getTotalCount(HashMap<String, String> params);
	
	/**
	 * 모든 메시지 출력 메서드
	 * @param params 시작 메시지 번호, 페이지 당 표시할 개수 , 현재 로그인한 사용자의 사용자번호
	 * @return DB에 등록된 모든 메시지를 담은 List
	 */
	public ArrayList<HashMap<String, Object>> selectList(HashMap<String, String> params);
	
	/**
	 * 메시지 쓰기 메서드
	 * @param params 송수신자 데이터, 메시지 내용
	 * @return 입력에 성공하였을 경우 기대값 1
	 */
	public int writeMessage(HashMap<String,String> params);
	
	/**
	 * 메시지 삭제 메서드
	 * @param params 삭제할 메시지의 메시지 번호
	 * @return 삭제에 성공하였을 경우 기대값 1
	 */
	public int deleteMessage(HashMap<String,String> params);
}
