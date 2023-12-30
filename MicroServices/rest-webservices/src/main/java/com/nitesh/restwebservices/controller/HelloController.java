package com.nitesh.restwebservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.restwebservices.bean.HelloWorldBean;

@RestController
public class HelloController {
	
	@Autowired
	private MessageSource messageSource;
	

	@GetMapping(path = "hello")
	public String sayHelloWorld() {
		return "Hello Nitesh!!!";
	}
	
	@GetMapping(path = "helloBean")
	public HelloWorldBean sayHelloWorldBean() {
		return new HelloWorldBean("Hello Nitesh!!!");
	}
	
	@GetMapping(path = "helloPath/{name}")
	public HelloWorldBean sayHelloWorldPath(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello, %s", name));
	}
	
	@GetMapping(path = "helloLang")
	public String sayHelloWorldI18n() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null,"Default Message",locale);
	}
}
