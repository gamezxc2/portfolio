package com.yhs.pf.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.yhs.pf.service.AttachService;

public class AttachJob extends QuartzJobBean {
	@Autowired AttachService aService;
	private Logger logger = Logger.getLogger(AttachJob.class);
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		logger.debug("AttachJob logger");
		// 스프링에서 자동 실행 되도록 하는 서포트
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		// 파일에서 연결이 끊어지는 것을 감지하여 linked 컬럼을 변경시키는 메서드
		aService.updateUnlinkedInfo();
	}
		
}
