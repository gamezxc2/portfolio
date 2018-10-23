package com.tj.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.tj.dao.MemberDao;
import com.tj.dto.Member;

public class MemberDaoImpl extends JdbcDaoSupport implements MemberDao{

	@Override
	public int join(Member m) {
		String sql="insert into member (member_id,member_pw,member_name,member_nick,email,birth_date,create_date)";
		sql += " VALUES (?, SHA2(MD5(?),512), ?, ?, ?, ?, NOW())";
		int result = getJdbcTemplate().update(sql, new Object[] {
				 m.getMemberId(), m.getMemberPw(), m.getMemberName(), 
				 m.getMemberNick(), m.getEmail(), m.getBirthDate()
		 });
		return result;
	}

	@Override
	public List<Member> findMember(String memberId) {
		String sql = "select member_idx,member_id,member_pw,member_name,member_nick,email,";
		sql += "DATE_FORMAT(birth_date , '%Y-%m-%d') as birth_date, ";
		sql += "DATE_FORMAT(create_date , '%Y-%m-%d') as create_date "; // Date_Format으로 날짜 변환
		sql += "from member where member_id = ?";
		// query -> List 출력 ,  queryForObject -> 1개일때만 출력.
		List<Member> m = getJdbcTemplate().query(sql, new Object[] { memberId } ,new BeanPropertyRowMapper<Member>(Member.class));
		/*
		Member m = new Member();
		Map<String,Object> result = getJdbcTemplate().queryForMap(sql,new Object[] { memberId });
		m.setMemberIdx((int)result.get("member_idx"));
		m.setMemberId((String)result.get("member_id"));
		m.setMemberPw((String)result.get("member_pw"));
		m.setMemberName((String)result.get("member_name"));
		m.setMemberNick((String)result.get("member_nick"));
		m.setEmail((String)result.get("email"));
		m.setBirthDate((String)result.get("birth_date"));
		m.setCreateDate((String)result.get("create_date"));
		*/	
		return m;
	}

	@Override
	public String makeCipherText(String memberPw) {
		String sql = "SELECT SHA2(MD5(?),512) as pw";
		String result = getJdbcTemplate().queryForObject(sql, new Object[] { memberPw }, String.class);
		return result;
	}

	
	
}
