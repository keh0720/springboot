package com.github.keh0720.springboot.quartz.service.impl;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.github.keh0720.springboot.quartz.service.QuartzService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService {
	
	private final Random random = new Random();
	
	public void run(String id) throws Exception {
		
		log.info("Running job on supervisor, job id " + id);
		if (random.nextInt(3) == 1) {
            throw new Exception("Randomly generated test exception on worker");
        }
        try {
            Thread.sleep(TimeUnit.MINUTES.toMillis(1));
        } catch (InterruptedException e) {
            log.error("Error", e);
        }
        log.info("Completed job on worker, job id " + id);

	}
}
