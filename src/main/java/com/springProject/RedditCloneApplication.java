package com.springProject;
import org.springframework.context.annotation.Import;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.springProject.config.OpenAPIConfiguration;

@SpringBootApplication
@EnableAsync
@Import(OpenAPIConfiguration.class)
public class RedditCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneApplication.class, args);
	}

}
