package com.yhs.pf.service;

import java.util.HashMap;
import java.util.List;

public interface AttachService {
	/**
	 * 파일 번호를 이용하여 1개의 파일 정보 불러오기
	 * @param fileIdx 파일 번호
	 * @return 해당하는 조건의 파일 정보 1개
	 */
	public HashMap<String, Object> getAttachFile(int fileIdx);
	
	/**
	 * 해당 조건에 해당하는 파일의 정보 불러오기
	 * @param typeSeq 게시판 번호
	 * @param boardSeq 게시글 번호
	 * @return 해당하는 글에 연결되어 있는 파일 정보를 반환
	 */
	public List<HashMap<String, Object>> getAttachFiles(int typeSeq, int boardSeq);
	
	/**
	 * 물리적 파일과 DB에 있는 정보 간에 연결이 끊어진 데이터를 찾아 특정 컬럼을 변경.
	 * @return update가 일어난 회수
	 */
	public int updateUnlinkedInfo();
}
