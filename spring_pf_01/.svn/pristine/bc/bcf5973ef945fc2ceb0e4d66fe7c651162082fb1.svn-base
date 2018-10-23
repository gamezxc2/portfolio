package com.yhs.pf.test.app;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yhs.pf.dto.Member;
import com.yhs.pf.service.BoardService;
import com.yhs.pf.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/applicationContext.xml"})
public class BoardTestApp {
	@Autowired private BoardService bService;
	@Autowired private MemberService mService;
	
	@Test //단위(기능) 테스트
	public void writeTest() { // ~~.do에 대응되는 메서드
		//웹에서 전송될 데이터를 유추해서 파라미터 생성
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("typeSeq", 1);
		params.put("memberIdx", 1);
		params.put("memberId", "testr");
		params.put("memberNick", "테스트");
		params.put("title", "Title is test");
		params.put("contents", "Content is test");
		Assert.assertEquals(1, bService.write(params));
		
		Member member = null;
		try {
			member = mService.login("aa", "bb");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Assert.assertNotNull(member);
	}
}
