package com.yhs.pf.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yhs.pf.dao.AttachDao;
import com.yhs.pf.service.AttachService;

@Service
public class AttachServiceImpl implements AttachService{
	private Logger logger = Logger.getLogger(AttachServiceImpl.class);
	@Autowired private AttachDao aDao;
	
	@Value("#{config['project.file.location']}")
	private String saveLocation;
	
	@Override
	public HashMap<String, Object> getAttachFile(int fileIdx) {		
		HashMap<String, Object> result = aDao.getAttachFile(fileIdx);		
		return result;
	}
	
	@Override
	public List<HashMap<String, Object>> getAttachFiles(int typeSeq, int boardSeq) {
		return aDao.getAttachFiles(typeSeq, boardSeq);
	}

	@Override
	public int updateUnlinkedInfo() {
		// 1. 첨부파일 정보 다 가져오기
		ArrayList<HashMap<String, Object>> list = aDao.getAllAttachFile();
		ArrayList<Integer> fileIdxs = new ArrayList<Integer>();
		// 2. 파일 있는지 없는지 확인
		for(HashMap<String,Object> target : list) {
			String fakeFilename = String.valueOf(target.get("fake_filename"));
			File f = new File(saveLocation, fakeFilename);
			logger.debug("updateUnlinkedInfo params = "+fakeFilename);
			logger.debug("updateUnlinkedInfo params = "+saveLocation);
			logger.debug("updateUnlinkedInfo params = "+f.exists());
			// 2-1. 없으면 컬럼(linked) 값 수정 
			if(!f.exists()) { // 파일이 물리적 주소에 존재하지 않는다면
				int fileIdx = Integer.parseInt(String.valueOf(target.get("file_idx")));
				fileIdxs.add(fileIdx);
				// int resultCnt = aDao.updateLinked(fileIdx); //1건씩
			}
		}
		//한꺼번에 업데이트
		int result = 0;
		if(fileIdxs.size() > 0) result = aDao.updateLinkedAll(fileIdxs);
		return result;
	}
		
}
