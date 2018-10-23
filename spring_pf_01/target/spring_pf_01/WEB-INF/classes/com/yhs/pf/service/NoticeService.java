package com.yhs.pf.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface NoticeService {
	/**
	 * 게시글 작성, 첨부파일이 있을 경우 첨부파일 정보도 같이 작성.
	 * @param params 게시판 번호, 사용자ID, 닉네임, 제목, 내용, 첨부파일여부
	 * @param mFile 첨부파일
	 * @return 작성이 완료 되었을 경우 기대값 1
	 */
	public int write(HashMap<String, Object> params, List<MultipartFile> mFile);
	
	/**
	 * 게시글 불러오기
	 * @param params 게시판 번호, 검색 옵션, 검색어
	 * @return 해당하는 조건의 모든 게시글을 가지는 List
	 */
	public List<HashMap<String,Object>> selectList(HashMap<String, Object> params); 
	
	/**
	 * 총 게시글 갯수 불러오기
	 * @param params 게시판 번호, 검색 옵션, 검색어
	 * @return 총 게시글 수(int)
	 */
	public int getTotalCount(HashMap<String, Object> params);
	
	/**
	 * 조회할 글 불러오기
	 * @param typeSeq 게시판 번호
	 * @param boardSeq 게시글 번호
	 * @return 해당 조건에 맞는 게시글 정보
	 */
	public HashMap<String, Object> getBoard(int typeSeq, int boardSeq);
	
	/**
	 * 해당 조건에 맞는 게시글 삭제하기
	 * @param typeSeq 게시판 번호
	 * @param boardSeq 게시글 번호
	 * @param hasFile 첨부파일 소지여부
	 * @return 게시글 삭제에 성공 하였을 경우 기대값 1
	 */
	public int deleteBoard(int typeSeq, int boardSeq, String hasFile);
	
	/**
	 * 게시글 내용 수정하기 , 수정할 경우에도 파일을 가지고 있을 경우 원 파일이름을 비교하여 다른 파일일 경우 업로드.
	 * @param params 게시판 번호, 게시글 번호, 제목, 내용, 첨부파일 여부
	 * @param mf 첨부파일을 가지고 있는 List
	 * @return 글 수정에 성공하였을 경우 기대값 1
	 */
	public int updateBoard(HashMap<String,Object> params, List<MultipartFile> mFile);
	
	/**
	 * 첨부파일 삭제.
	 * @param fileIdx 파일번호
	 * @param typeSeq 게시판 번호
	 * @param boardSeq 게시글 번호
	 * @return 첨부파일 삭제 결과를 표시해줄 안내 메시지 
	 */
	public String deleteAttach(int fileIdx, int typeSeq, int boardSeq);
	
	/**
	 * 게시글 추천하기
	 * @param params 해당 게시판 번호, 해당 게시글 번호, 사용자 ID
	 * @return 추천 결과에 따른 안내 메시지
	 */
	public String recommend(HashMap<String,Object> params);
}
