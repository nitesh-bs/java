package com.nitesh.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

	@Pointcut("execution(* com.nitesh.aopDemo.dao.*.*(..))")
	public void forDaoPackage() {
		
	}
	
	@Pointcut("execution(* com.nitesh.aopDemo.dao.*.set*(..))")
	public void setter() {
		
	}
	
	@Pointcut("execution(* com.nitesh.aopDemo.dao.*.get*(..))")
	public void getter() {
		
	}
	
	//combin pointcut expression
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNotGetterSetter() {
		
	}
	
}
