package com.tj.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.tj.dao.MemberDao;

public class MemberDaoImpl extends JdbcDaoSupport implements MemberDao{
	
	@Override
	public void join() {
		System.out.println("dao called");
	}

}
