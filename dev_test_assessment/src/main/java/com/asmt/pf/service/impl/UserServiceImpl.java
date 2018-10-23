package com.asmt.pf.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asmt.pf.dao.UserDao;
import com.asmt.pf.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired UserDao uDao;
	@Override
	public int join(HashMap<String, String> s) {
		return uDao.join(s);
	}
	@Override
	public int search(String userId) {
		return uDao.search(userId);
	}
	@Override
	public int delete(int idx) {
		return uDao.delete(idx);
	}

}
