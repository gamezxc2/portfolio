package com.tj.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tj.dto.Board;

public interface BoardDao {

	public int write(Board b);
	
	public int attach(HashMap<String,Object> ab);

	public List<Map<String, Object>> list(HashMap<String, String> params);
	
	public int getTotalCount(HashMap<String,String> params);
	
	public int updateHits(int typeSeq, int boardSeq);
	
	public Board getBoard(int typeSeq, int boardSeq);
	
	public int delete(int typeSeq, int boardSeq);

	public int update(Board b);

	public List<Map<String, Object>> getFile(int boardSeq, int typeSeq);

	public HashMap<String, Object> getAttachFile(int fileIdx);
	
	public int deleteAttach(int fileIdx);
		
	public int updateHasFile(int typeSeq, int boardSeq);
	
	public List<HashMap<String, Object>> getAttachFiles(int typeSeq, int boardSeq);

	public int deleteAttachSeq(int typeSeq, int boardSeq);
}
