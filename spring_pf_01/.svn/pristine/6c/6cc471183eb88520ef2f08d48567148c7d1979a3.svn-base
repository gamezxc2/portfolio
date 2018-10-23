package com.yhs.pf.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yhs.pf.dao.AttachDao;
import com.yhs.pf.dao.NoticeDao;
import com.yhs.pf.dao.RecommendDao;
import com.yhs.pf.service.NoticeService;
import com.yhs.pf.util.FileUtil;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired private NoticeDao nDao;
	@Autowired private AttachDao aDao;
	@Autowired private RecommendDao rDao;
	@Autowired private FileUtil fUtil;
	private Logger logger = Logger.getLogger(NoticeServiceImpl.class);
	@Value("#{config['project.file.location']}")
	private String saveLocation;
	
	@Override
	public int write(HashMap<String, Object> params, List<MultipartFile> mFile ) {
		int result = nDao.write(params); // 받은 게시글 정보를 DB에 등록
		if(params.get("hasFile").equals("1")) { // 첨부파일이 있을 경우에만 실행
			for(MultipartFile mp : mFile) {
				if(!mp.getOriginalFilename().equals("")) {
					String fakename = UUID.randomUUID().toString().replace("-", "");//파일명 중복을 피하기 위해 페이크명 
					params.put("fakeFilename", fakename); //파일 등록에 필요한 요소를 Map에 입력
					params.put("filename", mp.getOriginalFilename());
					params.put("fileSize", mp.getSize());
					params.put("fileType", mp.getContentType());
					params.put("saveLoc", saveLocation);
					try {
						fUtil.copyFile(mp, fakename); //물리적 위치에
					} catch (IOException e) {
						e.printStackTrace();
					}
					aDao.insertAttach(params); //첨부파일 정보 등록
				}
			}
		}
		return result;
	}
	
	@Override
	public List<HashMap<String,Object>> selectList(HashMap<String, Object> params) {
		return nDao.selectList(params);
	}
	@Override
	public int getTotalCount(HashMap<String, Object> params) {
		return nDao.getTotalCount(params);
	}
	@Override
	public HashMap<String, Object> getBoard(int typeSeq, int boardSeq) {
		nDao.updateHit(typeSeq, boardSeq); // 조회수 증가
		return nDao.getBoard(typeSeq, boardSeq); // 게시글 내용 읽어서 반환
	}
	@Override
	public int deleteBoard(int typeSeq, int boardSeq, String hasFile) {
		if(hasFile.equals("1")) { //첨부파일이 있으면
			//1. 삭제할 첨부파일 정보를 모두 가지고 온다.
			List<HashMap<String, Object>> files = aDao.getAttachFiles(typeSeq, boardSeq);
			//글번호 타입으로 첨부파일을 삭제하는 DAO 메서드 호출
			aDao.deleteAttachSeq(typeSeq, boardSeq);
			//게시 글을 삭제한 후 그 결과 실행 횟수를 result에 저장.
			int result = nDao.deleteBoard(typeSeq, boardSeq);
			//물리적 위치에서 삭제
			for(HashMap<String,Object> file : files) {
				fUtil.deleteFile(file);
			}
			return result; //첨부파일 정보를 가진 게시글 삭제시 이 시점에서 종료
		}
		return nDao.deleteBoard(typeSeq, boardSeq); 
	}
	
	@Override
	public int updateBoard(HashMap<String, Object> params, List<MultipartFile> mFile) {
		for(MultipartFile mp : mFile) {
			if(!mp.getOriginalFilename().equals("")) {
				String fakename = UUID.randomUUID().toString().replace("-", ""); //파일명 중복을 피하기 위해 페이크명 
				params.put("filename", mp.getOriginalFilename()); //파일 등록에 필요한 요소를 Map에 입력
				params.put("fileSize", String.valueOf(mp.getSize()));
				params.put("fileType", mp.getContentType());
				params.put("fakeFilename", fakename);
				params.put("saveLoc", saveLocation);
				logger.debug("updateBoard params = "+params);
				aDao.insertAttach(params);
				try {
					fUtil.copyFile(mp, fakename);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		int result = nDao.updateBoard(params);
		return result;
	}

	@Override
	public String deleteAttach(int fileIdx, int typeSeq, int boardSeq) {
		String msg = "";
		HashMap<String, Object> fileInfo = aDao.getAttachFile(fileIdx);
		//DB에서 첨부파일 정보를 삭제한다.
		int resultCnt = aDao.deleteAttach(fileIdx);
		// 삭제한 원 게시글과 첨부파일 정보의 관계를 확인
		List<HashMap<String, Object>> files = aDao.getAttachFiles(typeSeq, boardSeq);
		// 가져온 첨부파일이 없으면 (더이상 첨부파일이 없으면), 게시글의 has_file을 바꾼다
		if(files == null || files.size() == 0) {
			//게시글의 has_file을 0으로 바꾼다.
			nDao.updateHasFile(typeSeq, boardSeq);
		}
		// deleteAttach에 성공, updateHasFile에 결과 값이 같을 경우. 물리 디스크에서 삭제한다.
		if(resultCnt == 1 ) {
			if(fUtil.deleteFile(fileInfo)) {
				msg = "해당 파일의 삭제가 완료 되었습니다";
			} else {
				msg = "파일 삭제 중 에러가 발생했습니다. 관리자에게 문의하시기 바랍니다.";
			}
		} else {
			msg = "파일 삭제에 실패하셨습니다.";
		}
		return msg;
	}

	@Override
	public String recommend(HashMap<String, Object> params) {
		if(rDao.countRecmnd(params) == 0) { // 추천 테이블에 현 추천인 정보가 없을 경우(중복 추천이 아닐 경우) 
			if(nDao.recommend(params)  == 1) { // 추천 수 1 증가
				//추천 한 추천인의 정보를 추천 테이블에 등록
				return (rDao.insertRecmnd(params) ==1)? "추천되었습니다" : "시스템 에러! 관리자에게 문의하세요";
			}
			return "추천에 실패하셨습니다."; // 추천수 증가가 되지 않은 경우
		}
		return "이미 추천한 게시글 입니다."; // 이미 해당 게시글에 추천하였을 경우
	}
}
