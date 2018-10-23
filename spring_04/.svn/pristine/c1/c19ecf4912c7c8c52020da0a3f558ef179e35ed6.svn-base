package com.tj.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tj.dao.MemberDao;
import com.tj.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao{
	@Autowired private JdbcTemplate jdbcTemplate;
	@Override
	public int join(HashMap<String, String> params) {
		String sql="insert into member (member_id,member_pw,member_name,member_nick,email,birth_date,create_date)";
		sql += " VALUES (?, SHA2(MD5(?),512), ?, ?, ?, ?, NOW())";
		int result = jdbcTemplate.update(sql, new Object[] {
				params.get("memberId"), params.get("memberPw"), params.get("memberName"),
				params.get("memberNick"), params.get("email"), params.get("birthDate")
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
		List<Member> m = jdbcTemplate.query(sql, new Object[] { memberId } ,new BeanPropertyRowMapper<Member>(Member.class));
		return m;
	}

	@Override
	public String makeCipherText(String memberPw) {
		String sql = "SELECT SHA2(MD5(?),512) as pw";
		String result = jdbcTemplate.queryForObject(sql, new Object[] { memberPw }, String.class);
		return result;
	}//암호문 생성하는 메서드. 

}
