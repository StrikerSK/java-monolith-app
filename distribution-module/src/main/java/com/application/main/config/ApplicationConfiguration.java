package com.application.main.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

	@Bean
	public PropertiesFactoryBean countryOptions(){
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("properties/countries.properties"));
		return bean;
	}

	@Bean
	public List<String> programingLanguagesList() {
		return Stream.of("C", "C++", "C#", "Objective-C", "Golang", "Java", "Javascript", "Kotlin", "Python", "R",
				"Ruby", "Rust", "Scala", "Swift", "Typescript").collect(Collectors.toList());
	}

	@Bean
	public List<String> spokenLanguagesList() {
		return Stream.of("Arabic", "Chinese", "Czech", "Dutch", "English", "French", "German", "Hebrew", "Italian",
				"Polish", "Portuguese", "Russian", "Slovak", "Spanish", "Vietnamese").collect(Collectors.toList());
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
}
