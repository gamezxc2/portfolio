package com.asmt.test;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asmt.pf.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources-local/applicationContext-beans.xml", 
			"file:src/main/resources-local/applicationContext-datasource.xml"})
public class UserTestApp {
	@Autowired UserService uService;
	@Test
	public void test01() {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("userId", "doyouknowme");
		params.put("userPw", "tellmewhoiam");
		params.put("userName", "테스트");
		params.put("userNick", "테스트입니다");
		params.put("email", "doyouknowme@test.com");
		params.put("isAdmin", "N");
		Assert.assertEquals(1, uService.join(params));
		int idx = uService.search("doyouknowme");
		Assert.assertNotNull(idx);
		Assert.assertEquals(1, uService.delete(idx));
	}
}
