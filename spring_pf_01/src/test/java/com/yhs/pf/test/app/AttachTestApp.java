package com.yhs.pf.test.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yhs.pf.service.AttachService;

/**
 * 컨트롤러의 역활로 테스트.
 * @author user
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/applicationContext.xml"})
public class AttachTestApp {
	@Autowired AttachService aService;
		
	@Test
	public void test01() {
		System.out.println("aService linked result : "+aService.updateUnlinkedInfo());
	}
}
