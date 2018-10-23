package com.tj.util;

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
	
	public byte[] readFile(HashMap<String, Object> fileInfo) {
		//파일찾기
		logger.debug("util find fakename ---- "+String.valueOf(fileInfo.get("fake_filename")));
		File f = new File(saveLocation, String.valueOf(fileInfo.get("fake_filename")));
		
		byte[] b = null;
		
		if(f.exists()) {
			try {
				b = FileUtils.readFileToByteArray(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
		}
		return b;
	}
	
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
