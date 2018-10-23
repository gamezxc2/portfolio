package com.yhs.pf.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	Logger logger = Logger.getLogger(FileUtil.class);
	
	@Value("#{config['project.file.location']}")
	private String saveLocation;
	//private String saveLocation = "c:/dev/upload";
	
	/**
	 * 파일을 받아와 복사하여 지정 경로에 저장하는 메서드
	 * @param mf 파일 
	 * @param fakename 저장할 파일 명
	 * @throws IOException 예외
	 */
	public void copyFile(MultipartFile mf, String fakename) throws IOException {
		//폴더 지정
		File ddir = new File(this.saveLocation);
		//디렉토리 확인 후 없으면 생성
		if(!ddir.exists()) {
			ddir.mkdirs();
		}
		//파일 지정
		File dfile = new File(ddir, fakename);
		FileCopyUtils.copy(mf.getBytes(), dfile);
	}
	
	
	/**
	 * 물리적 위치에 있는 파일을 바이트 단위의 배열로 읽어와 반환한다.
	 * @param fileInfo 파일에 대한 정보
	 * @return 해당 파일을 바이트 단위로 복사한 배열.
	 */
	public byte[] readFile(HashMap<String, Object> fileInfo) {
		//파일찾기
		logger.debug("util find fakename ---- "+String.valueOf(fileInfo.get("fake_filename")));
		File f = new File(saveLocation, String.valueOf(fileInfo.get("fake_filename")));
		
		byte[] b = null;
		
		if(f.exists()) {
			try {
				b = FileUtils.readFileToByteArray(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return b;
	}
	
	
	/**
	 * 물리적 주소에 존재하는 파일이 있을 경우 삭제하는 메서드
	 * @param fileInfo 파일에 대한 정보
	 * @return 삭제에 성공했는지에 대한 여부를 반환
	 */
	public boolean deleteFile(HashMap<String, Object> fileInfo) {
		File f = new File(saveLocation, String.valueOf(fileInfo.get("fake_filename")));
		if(f.exists()) { //물리적 위치에 존재하면 지운다.
			logger.debug("deleteFile params = "+f.exists());
			logger.debug("deleteFile params = "+fileInfo);
			return f.delete();
		}
		return false;
	}
}	