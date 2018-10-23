package com.tj.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.tj.controller.BoardController;
import com.tj.dao.BoardDao;
import com.tj.dto.Board;
import com.tj.service.BoardService;
import com.tj.util.FileUtil;

@Service
public class BoardServiceImpl implements BoardService{
	
	private Logger logger = Logger.getLogger(BoardController.class);
	
	@Autowired private BoardDao bDao;
	@Autowired private FileUtil fUtil;

	@Override
	public int write(Board b, List<MultipartFile> mf) {
		int resultCnt = bDao.write(b);
		//System.out.println("After boardSeq --- "+b.getBoardSeq());
		
		//첨부파일 있으면 board_attach 테이블에 등록
		if(b.getHasFile().equals("1")) {
			HashMap<String, Object> ab = new HashMap<>();
			for(MultipartFile mp : mf) {
				ab.put("boardSeq", b.getBoardSeq());
				ab.put("boardType", b.getTypeSeq());
				ab.put("filename", mp.getOriginalFilename());
				ab.put("fileSize", mp.getSize());
				ab.put("fileType", mp.getContentType());
				if(!mp.getOriginalFilename().equals("")) {
					String fakename = UUID.randomUUID().toString().replace("-", "");
					ab.put("fakeFilename", fakename);
					try {
						fUtil.copyFile(mp, fakename);
					} catch (IOException e) {
						e.printStackTrace();
					}
					bDao.attach(ab);
				}
			}
		}
		return resultCnt;
	}

	@Override
	public List<Map<String, Object>> list(HashMap<String, String> params) {
		logger.debug("service list params ---- "+params);
		List<Map<String, Object>> result = bDao.list(params);
		return result;
	}

	@Override
	public int getTotalCount(HashMap<String,String> params) {
		return bDao.getTotalCount(params);
	}

	@Override
	public Board read(int typeSeq, int boardSeq ) {
		bDao.updateHits(typeSeq, boardSeq);
		Board result = bDao.getBoard(typeSeq, boardSeq);
		
		return result;
	}

	@Override
	public List<Map<String, Object>> getFile(int boardSeq, int typeSeq) {
		List<Map<String, Object>> result = bDao.getFile(boardSeq, typeSeq);
		logger.debug("getFile result ---- "+result);
		return result;
	}
	
	@Override
	public int delete(int typeSeq, int boardSeq, String hasFile) {
		if(hasFile.equals("1")) { //첨부파일이 있으면
			//1. 삭제할 첨부파일 정보를 모두 가지고 온다.
			List<HashMap<String, Object>> files = bDao.getAttachFiles(typeSeq, boardSeq);
			//글번호 타입으로 첨부파일을 삭제하는 DAO 메서드 호출
			bDao.deleteAttachSeq(typeSeq, boardSeq);
			//글 삭제.
			int result = bDao.delete(typeSeq, boardSeq);
			//물리적 위치에서 삭제
			for(HashMap<String,Object> file : files) {
				fUtil.deleteFile(file);
			}
			return result;
		}
		return bDao.delete(typeSeq, boardSeq); 
	}

	@Override
	public int update(Board b, List<MultipartFile> mf) {
		HashMap<String, Object> ab = new HashMap<>(); //params 사용시 생성안하고 바로 put
		for(MultipartFile mp : mf) {
			if(!mp.getOriginalFilename().equals("")) {
				String fakename = UUID.randomUUID().toString().replace("-", "");
				ab.put("boardSeq", b.getBoardSeq());
				ab.put("boardType", b.getTypeSeq());
				ab.put("filename", mp.getOriginalFilename());
				ab.put("fileSize", mp.getSize());
				ab.put("fileType", mp.getContentType());
				ab.put("fakeFilename", fakename);
				bDao.attach(ab);
				try {
					fUtil.copyFile(mp, fakename);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		int result = bDao.update(b);
		return result;
	}

}
