package com.github.keh0720.springboot.quartz.service;

import java.util.Random;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@EnableScheduling
public class JobRunner {
	
//	@Autowired JobsService js;
	private final JobsService js;

    @Autowired
    public JobRunner(JobsService jobsService) {
        this.js = jobsService;
    }
    
    @Scheduled(fixedDelay = 10000)
    private void initRunner() throws InterruptedException {
    	log.debug("job runner init");
		
		String jobId;
		try {
			jobId = js.addNewJob();
			log.debug("created new job : {}", jobId);
			
			log.debug("jobs : {}", js.getJobs());
			
			Random random = new Random();
			
			Thread.sleep((random.nextInt(60) + 20) * 1000);
			
			log.debug("deleted job [{}] result {}", jobId, js.deleteJob(jobId));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }
	
}
