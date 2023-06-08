package com.springProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.v3.oas.models.OpenAPI;
import springfox.documentation.common.ExternalDocumentation;
import springfox.documentation.service.ApiInfo;
import io.swagger.*;
@Configuration
public class OpenAPIConfiguration {
	@Bean
    public OpenAPI expenseAPI() {
		 /*return new OpenAPI()
	              .info(new Info().title("Reddit Clone API")
	                        .description("API for Reddit Clone Application")
	                        .version("v0.0.1")
	                        .license(new License().name("Apache License Version 2.0").url("http://programmingtechie.com")))
	                .externalDocs(new ExternalDocumentation()
	                        .description("Expense Tracker Wiki Documentation")
	                        .url("https://expensetracker.wiki/docs"));*/
		OpenAPI openApi = new OpenAPI();
		Info info = new Info()
		.title("open api")
		.description("descreption")
		.version("1.0.0");
		return openApi;
		}
}
