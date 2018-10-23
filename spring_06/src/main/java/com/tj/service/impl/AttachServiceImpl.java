package com.tj.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tj.dao.BoardDao;
import com.tj.service.AttachService;
import com.tj.util.FileUtil;

@Service
public class AttachServiceImpl implements AttachService{
	@Autowired private BoardDao bDao;
	@Autowired private FileUtil fUtil;
	
	@Override
	public HashMap<String, Object> getAttachFile(int fileIdx) {		
		HashMap<String, Object> result = bDao.getAttachFile(fileIdx);		
		return result;
	}
	
	@Override
	public List<HashMap<String, Object>> getAttachFiles(int typeSeq, int boardSeq) {
		return bDao.getAttachFiles(typeSeq, boardSeq);
	}
	
	@Override
	public boolean deleteAttach(int fileIdx, int typeSeq, int boardSeq) {
		//noticeService
		boolean result = false;
		HashMap<String, Object> fileInfo = this.getAttachFile(fileIdx);
		//DB에서 첨부파일 정보를 삭제한다.
		result = (bDao.deleteAttach(fileIdx) == 1);
		// 원 게시글과 첨부파일 정보의 관계를 확인
		List<HashMap<String, Object>> files = bDao.getAttachFiles(typeSeq, boardSeq);
		// 가져온 첨부파일이 없으면 (더이상 첨부파일이 없으면), 게시글의 has_file을 바꾼다
		if(files == null || files.size() == 0) {
			//게시글의 has_file을 0으로 바꾼다.
			result = (bDao.updateHasFile(typeSeq, boardSeq) ==1 && result);
		}
		//물리 디스크에서 삭제한다.
		result = (fUtil.deleteFile(fileInfo) == result);
		return result;
	}
	
}
