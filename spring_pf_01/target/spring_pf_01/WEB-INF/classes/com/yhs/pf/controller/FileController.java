package com.yhs.pf.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {
	@Autowired
	ApplicationContext context;
	
	/**
	 * 링크 클릭시 비동기 통신으로 저장된 위치의 
	 * @param resp 리스폰스를 받기위한 변수
	 * @return 파일
	 */
	@ResponseBody
	@RequestMapping("/file/downloadErd.do")
	public byte[] erd(HttpServletResponse resp) {
		try {
			File file = context.getResource("classpath:/files/portfolio.mwb").getFile();
			String encodingName = java.net.URLEncoder.encode(file.getName(),"UTF-8");
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + encodingName+"\"");
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setContentLength((int)file.length());
			return FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
