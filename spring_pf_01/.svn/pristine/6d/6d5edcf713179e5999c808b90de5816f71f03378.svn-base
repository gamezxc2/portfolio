package com.yhs.pf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface AttachDao {
	/**
	 * 첨부파일에 대한 정보를 받아서 DB에 등록
	 * @param params 게시판 번호, 게시글 번호, 파일 이름, 가짜 파일이름, 파일 크기, 파일 속성
	 * @return 첨부파일 정보 등록에 성공시 기대값 1
	 */
	public int insertAttach(HashMap<String,Object> params);
		
	/**
	 * 파일 번호로 검색하여 해당하는 첨부파일 정보를 조회
	 * @param fileIdx 파일 번호
	 * @return 해당 조건을 만족하는 파일 정보 1개(Map)
	 */
	public HashMap<String, Object> getAttachFile(int fileIdx);
	
	/**
	 * 해당 파일 번호를 가지는 파일 정보 삭제
	 * @param fileIdx 파일 번호
	 * @return 삭제에 성공하였을 경우 기대값 1
	 */
	public int deleteAttach(int fileIdx);
	
	/**
	 * 게시판 번호, 게시글 번호로 검색하여 해당하는 모든 첨부파일 정보를 조회
	 * @param typeSeq 게시판 번호
	 * @param boardSeq 게시글 번호
	 * @return 해당 조건을 만족하는 모든 첨부파일 정보를 가지는 List
	 */
	public List<HashMap<String, Object>> getAttachFiles(int typeSeq, int boardSeq);
	
	/**
	 * 게시판 번호와 게시글 번호로 검색하여 해당 조건에 맞는 첨부파일 정보를 삭제
	 * @param typeSeq 게시판 번호
	 * @param boardSeq 게시글 번호
	 * @return 첨부파일 정보 삭제에 성공하였을 경우 기대값 1
	 */
	public int deleteAttachSeq(int typeSeq, int boardSeq);
	
	/**
	 * 모든 첨부파일 정보를 조회
	 * @return DB에 입력된 모든 첨부파일 정보.
	 */
	public ArrayList<HashMap<String, Object>> getAllAttachFile();
	
	/**
	 * 파일 연결이 끊어진 파일의 연결 확인 컬럼 값을 변경하는 메서드
	 * @param fileIdx 연결이 끊어진 첨부파일 정보의 색인 번호
	 * @return update 성공시 기대값 1
	 */
	public int updateLinked(int fileIdx);
	
	/**
	 * 파일 연결이 끊어진 파일들의 모든 파일 색인 번호를 받아 연결 확인 컬럼 값을 한번에 변경.
	 * @param fileIdxs 연결이 끊어진 첨부파일 정보의 색인 번호를 전부 저장한 리스트
	 * @return update 성공한 횟수.
	 */
	public int updateLinkedAll(ArrayList<Integer> fileIdxs);
}
