package com.tj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.tj.dto.Board;

public interface BoardService {

	public int write(Board b, List<MultipartFile> mf);

	public List<Map<String, Object>> list(HashMap<String, String> params);
	
	public int getTotalCount(HashMap<String,String> params);
	
	public Board read(int typeSeq, int boardSeq);
	
	public int delete(int typeSeq, int boardSeq, String hasFile);

	public int update(Board b, List<MultipartFile> mf);

	public List<Map<String, Object>> getFile(int boardSeq, int typeSeq);
	
}
