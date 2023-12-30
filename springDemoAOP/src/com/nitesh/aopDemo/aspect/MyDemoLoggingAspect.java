package com.nitesh.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

//	@Before("execution(public void add*())")
	
//	@Before("execution(void add*())")
	
//	@Before("execution(* add*(com.nitesh.aopDemo.Account))")
	
//	@Before("execution(* add*(..))")
	
	@Before("execution(* com.nitesh.aopDemo.dao.*.*(..))")
	public void beforeAddAccountAdvice() {
		System.out.println("=== : MyDemoLoggingAspect -> beforeAddAccountAdvice");
	}
	
}
