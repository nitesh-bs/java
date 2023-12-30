package com.nitesh.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {

	@Before("com.nitesh.aopDemo.aspect.AopExpressions.forDaoPackageNotGetterSetter()")
	public void APIbeforeAdviceAccountAdvice() {
		System.out.println("=== : MyApiAnalyticsAspect -> APIbeforeAdviceAccountAdvice");
	}
}
