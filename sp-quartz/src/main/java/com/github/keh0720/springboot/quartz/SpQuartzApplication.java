package com.github.keh0720.springboot.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.github.keh0720.springboot.quartz.conf.QuatzJobConfig;
import com.github.keh0720.springboot.quartz.service.JobsListenerService;

@SpringBootApplication
@Import({QuatzJobConfig.class, JobsListenerService.class})
public class SpQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpQuartzApplication.class, args);
	}

}
