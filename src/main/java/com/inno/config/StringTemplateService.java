package com.inno.config;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Service
public class StringTemplateService {

	TemplateEngine templateEngine;

	private final static String TEMPLATE_LOCAL = "US";

	@Autowired
	public TemplateEngine getTemplateEngine() {
		templateEngine = new TemplateEngine();
		StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
		templateEngine.setTemplateResolver(stringTemplateResolver);
		return templateEngine;
	}

	public String getTemplateFromAttributes(String htmlContent, Map<String, Object> attr) {
		templateEngine = getTemplateEngine();
		Context context = new Context(new Locale(TEMPLATE_LOCAL));
		if (!CollectionUtils.isEmpty(attr)) {
			attr.forEach((k, v) -> context.setVariable(k, v));
		}
		return templateEngine.process(htmlContent, context);
	}
}