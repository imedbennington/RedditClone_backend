package com.springProject.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class mailContentBuilder {
	private static final TemplateEngine templateEngine = new TemplateEngine();
	public static String build (String message) {
		Context context = new Context();
		context.setVariable("message", context);
		return  templateEngine.process("mailTemplate", context);
	}
}
