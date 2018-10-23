package com.yhs.pf.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface MessageService {
	
	/**
	 * 메시지 총합 개수 확인
	 * @param params 
	 * @return DB에 저장된 메시지의 총 개수
	 */
	public int getTotalCount(HashMap<String,String> params);
	
	/**
	 * 메시지 목록 불러오기
	 * @param params 시작 메시지 번호, 페이지 당 표시할 개수 , 현재 로그인한 사용자의 사용자번호
	 * @return DB에 등록된 모든 메시지를 담은 List
	 */
	public ArrayList<HashMap<String,Object>> selectList(HashMap<String,String> params);
	
	/**
	 * 메시지 보내기
	 * @param params 송수신자에 관한 정보, 보내는 메시지 내용
	 * @return 메시지 입력시 기대값 1
	 */
	public int sendMessage(HashMap<String,String> params);
	
	/**
	 * 메시지 지우기
	 * @param params 삭제할 메시지의 정보
	 * @return 메시지 삭제시 기대값 1
	 */
	public int deleteMessage(HashMap<String,String> params);
}
