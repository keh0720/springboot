package com.github.keh0720.springboot.quartz.conf;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.github.keh0720.springboot.quartz.service.JobsListenerService;

import liquibase.integration.spring.SpringLiquibase;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
@EnableAsync
@EnableScheduling
public class QuatzJobConfig {

	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext,
			// Injecting SpringLiquibase to ensure liquibase is already initialized and
			// created the Quartz tables
			SpringLiquibase springLiquibase) {
		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory,
			JobsListenerService jobsListenerService) throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJobFactory(jobFactory);
		factory.setQuartzProperties(quartzProperties());
		factory.setGlobalJobListeners(jobsListenerService);
		// https://medium.com/@rudra.ramesh/use-following-code-in-supervisor-app-while-creating-schedulerfactorybean-object-now-supervisor-fd2f95365350
		// If you need to disable launching of jobs on supervisor use this:
		// factory.setAutoStartup(false);
		return factory;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}
}
