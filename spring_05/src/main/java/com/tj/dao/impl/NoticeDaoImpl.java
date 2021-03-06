package com.tj.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tj.dao.NoticeDao;

@Repository
public class NoticeDaoImpl implements NoticeDao{
	@Autowired private JdbcTemplate jdbcTemplate;

	@Override
	public int write(HashMap<String, String> params) {
		String sql = "INSERT INTO board (`type_seq`, `member_id`, `member_nick`, `title`, `content`, `create_date`)";
		sql = sql.concat(" VALUES (?, ?, ?, ?, ?,  NOW() );");
		return jdbcTemplate.update(sql,
				params.get("typeSeq"),
				params.get("memberId"),
				params.get("memberNick"),
				params.get("title"),
				params.get("contents")
		);
		
	}

	@Override
	public List<Map<String,Object>> selectList(HashMap<String, String> params) {
		String sql = "SELECT * FROM board WHERE type_seq = 1 ORDER BY board_seq DESC LIMIT ?, ?"; //board의 모든 column을 조회하여
		//DTO로 사용자 지정 객체를 제네릭으로 지정하는 경우 query+rowMapper / Map으로 출력할 경우엔 queryForList
		return jdbcTemplate.queryForList(sql, new Object[] {Integer.parseInt(params.get("startIdx")), Integer.parseInt( params.get("pageArticleSize"))});
		//List<Map>로 출력
	}

	@Override
	public int getTotalCount() {
		String sql = "SELECT COUNT(board_seq) FROM board WHERE type_seq = 1";
		
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int updateHit(int typeSeq, int boardSeq) {
		String sql = "UPDATE board SET hits=hits+1 WHERE type_seq = ? AND board_seq = ?";
		return jdbcTemplate.update(sql, new Integer[] {typeSeq, boardSeq});
	}

	@Override
	public Map<String, Object> getBoard(int typeSeq, int boardSeq) {
		String sql = "SELECT * FROM board WHERE type_seq = ? AND board_seq = ? ";
		return jdbcTemplate.queryForMap(sql, new Integer[] {typeSeq, boardSeq});
	}

	@Override
	public int deleteBoard(int typeSeq, int boardSeq) {
		String sql = "DELETE FROM board WHERE type_seq = ? AND board_seq = ? ";
		return jdbcTemplate.update(sql, new Integer[] {typeSeq, boardSeq});
	}

	@Override
	public int updateBoard(HashMap<String, String> params) {
		String sql = "UPDATE board SET `title` = ?, `content` = ?, `update_date` = NOW() WHERE type_seq = ? AND board_seq = ?";
		return jdbcTemplate.update(sql,
				params.get("title"),
				params.get("contents"),
				params.get("typeSeq"),
				params.get("boardSeq")
		);
	}
	
}
