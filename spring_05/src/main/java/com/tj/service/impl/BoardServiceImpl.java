package com.tj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tj.dao.BoardDao;
import com.tj.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired private BoardDao bDao;
	@Override
	public int write(HashMap<String, String> params) {
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
		return (bDao.updateHit(typeSeq, boardSeq) == 1) ? bDao.getBoard(typeSeq, boardSeq) : null;
	}
	@Override
	public int deleteBoard(int typeSeq, int boardSeq) {
		return bDao.deleteBoard(typeSeq, boardSeq);
		//return 0;
	}
	@Override
	public int updateBoard(HashMap<String, String> params) {
		return bDao.updateBoard(params);
		//return 0;
	}
	
}
