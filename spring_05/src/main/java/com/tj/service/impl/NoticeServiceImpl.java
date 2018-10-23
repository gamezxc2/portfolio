package com.tj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tj.dao.NoticeDao;
import com.tj.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired private NoticeDao nDao;
	@Override
	public int write(HashMap<String, String> params) {
		return nDao.write(params);
	}
	@Override
	public List<Map<String,Object>> selectList(HashMap<String, String> params) {
		return nDao.selectList(params);
	}
	@Override
	public int getTotalCount() {
		return nDao.getTotalCount();
	}
	@Override
	public Map<String, Object> getBoard(int typeSeq, int boardSeq) {
		return (nDao.updateHit(typeSeq, boardSeq) == 1) ? nDao.getBoard(typeSeq, boardSeq) : null;
	}
	@Override
	public int deleteBoard(int typeSeq, int boardSeq) {
		return nDao.deleteBoard(typeSeq, boardSeq);
		//return 0;
	}
	@Override
	public int updateBoard(HashMap<String, String> params) {
		return nDao.updateBoard(params);
		//return 0;
	}
}
