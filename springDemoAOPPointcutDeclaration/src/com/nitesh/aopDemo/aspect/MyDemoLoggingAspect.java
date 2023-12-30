package com.nitesh.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

//	@Before("execution(public void add*())")
	
//	@Before("execution(void add*())")
	
//	@Before("execution(* add*(com.nitesh.aopDemo.Account))")
	
//	@Before("execution(* add*(..))")
	
	@Pointcut("execution(* com.nitesh.aopDemo.dao.*.*(..))")
	private void forDaoPackage() {
		
	}
	
	@Before("forDaoPackage()")
	public void beforeAddAccountAdvice() {
		System.out.println("=== : MyDemoLoggingAspect -> beforeAddAccountAdvice");
	}
	
	
	@Before("forDaoPackage()")
	public void beforeAdviceAccountAdvice() {
		System.out.println("=== : MyDemoLoggingAspect -> beforeAdviceAccountAdvice");
	}
}
