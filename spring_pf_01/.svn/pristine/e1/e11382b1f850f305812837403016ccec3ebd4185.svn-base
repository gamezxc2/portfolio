package com.yhs.pf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhs.pf.dao.BoardDao;
import com.yhs.pf.dao.RecommendDao;
import com.yhs.pf.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired private BoardDao bDao;
	@Autowired private RecommendDao rDao;
	@Override
	public int write(HashMap<String, Object> params) {
		return bDao.write(params);
	}
	@Override
	public List<Map<String,Object>> selectList(HashMap<String, String> params) {
		return bDao.selectList(params);
	}
	@Override
	public int getTotalCount(HashMap<String, String> params) {
		return bDao.getTotalCount(params);
	}
	@Override
	public Map<String, Object> getBoard(int typeSeq, int boardSeq) {
		//조회수 1 증가에 성공하였을 경우에만 게시판 내용을 불러온다.
		return (bDao.updateHit(typeSeq, boardSeq) == 1) ? bDao.getBoard(typeSeq, boardSeq) : null;
	}
	@Override
	public int deleteBoard(int typeSeq, int boardSeq) {
		rDao.deleteRecmnd(boardSeq, typeSeq);
		return bDao.deleteBoard(typeSeq, boardSeq);
	}
	@Override
	public int updateBoard(HashMap<String, String> params) {
		return bDao.updateBoard(params);
	}
	@Override
	public String recommend(HashMap<String, Object> params) {
		if(rDao.countRecmnd(params) == 0) { // 추천 테이블에 현 추천인 정보가 없을 경우(중복 추천이 아닐 경우) 
			if(bDao.recommend(params)  == 1) { // 추천 수 1 증가
				//추천 한 추천인의 정보를 추천 테이블에 등록
				return (rDao.insertRecmnd(params) ==1)? "추천되었습니다" : "시스템 에러! 관리자에게 문의하세요";
			}
			return "추천에 실패하셨습니다."; // 추천수 증가가 되지 않은 경우
		}
		return "이미 추천한 게시글 입니다."; // 이미 해당 게시글에 추천하였을 경우
	}
	

	
}
