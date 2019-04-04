package com.github.keh0720.springboot.quartz.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.github.keh0720.springboot.quartz.service.QuartzService;

public class QuartzJob extends QuartzJobBean {

	@Autowired
	QuartzService qs;
	
//	@Override
//	public void execute(JobExecutionContext context) throws JobExecutionException {
//	
//		try {
//            String id = context.getJobDetail().getKey().getName();
//            qs.run(id);
//        } catch (Exception e) {
//            throw new JobExecutionException(e);
//        }
//
//	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		try {
            String id = context.getJobDetail().getKey().getName();
            qs.run(id);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }

	}

}
