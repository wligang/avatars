
package com.wlgdo.avatar.activiti.config;

import lombok.AllArgsConstructor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author Ligang.Wang
 * Activiti 配置
 */
@Configuration
@AllArgsConstructor
public class ActivitiConfig {
	private final DataSource dataSource;
	private final PlatformTransactionManager transactionManager;

	@Bean
	public SpringProcessEngineConfiguration getProcessEngineConfiguration() {
		SpringProcessEngineConfiguration config =
				new SpringProcessEngineConfiguration();
		// 流程图字体设置
		config.setActivityFontName("宋体");
		config.setAnnotationFontName("宋体");
		config.setLabelFontName("宋体");

		config.setDataSource(dataSource);
		config.setTransactionManager(transactionManager);
		config.setDatabaseType("mysql");
		config.setDatabaseSchemaUpdate("true");
		return config;
	}

	@Bean
	@Primary
	public TaskExecutor primaryTaskExecutor() {
		return new ThreadPoolTaskExecutor();
	}
}
