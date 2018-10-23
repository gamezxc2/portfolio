package com.yhs.pf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhs.pf.dao.MemberDao;
import com.yhs.pf.dao.MessageDao;
import com.yhs.pf.dto.Member;
import com.yhs.pf.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	@Autowired private MemberDao mDao;
	@Autowired private MessageDao meDao;
	
	@Override
	public int getTotalCount(HashMap<String, String> params) {
		return meDao.getTotalCount(params);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectList(HashMap<String, String> params) {
		return meDao.selectList(params);
	}

	@Override
	public int sendMessage(HashMap<String, String> params) {
		Member m = mDao.findMember(params.get("receiveId"));
		if(m.getMemberId().equals(params.get("receiveId"))) {
			params.put("receiveNick", m.getMemberNick());
			return meDao.writeMessage(params);
		}
		return 0;
	}

	@Override
	public int deleteMessage(HashMap<String, String> params) {
		return meDao.deleteMessage(params);
	}

}
