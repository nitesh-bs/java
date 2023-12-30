package com.nitesh.aopDemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.nitesh.aopDemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName()); 
	
	@Around("execution(* com.nitesh.aopDemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		String method = proceedingJoinPoint.getSignature().toShortString();
		myLogger.info("======> : @around on method : "+method);
		
		long begin = System.currentTimeMillis();
		
		Object result = proceedingJoinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		myLogger.info("======> Duration : "+duration/1000.0+ " seconds");

		return result;
	}
	
	@After("execution(* com.nitesh.aopDemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("======> : @after (finally) on method : "+method);

	}
	
	@AfterThrowing(
			pointcut = "execution(* com.nitesh.aopDemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc"
			)
	public void afterthrowingFindAccountAdvice(JoinPoint joinPoint,Throwable theExc) {
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("======> : @afterThrowing on method : "+method);

		myLogger.info("------> : exceptions is : "+theExc);
	}
	
	@AfterReturning(pointcut = "execution(* com.nitesh.aopDemo.dao.AccountDAO.findAccounts(..))",returning = "result")
	public void afterReturningFindAccountAdvice(JoinPoint joinPoint,List<Account> result) {
		
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("======> : @afterReturning on method : "+method);
		myLogger.info("======> : result : "+result);
		
		if(result != null) {
			convertAccountNameToUpperCase(result);	
		}
		
		myLogger.info("======> : result modified : "+result);
	}
	
	private void convertAccountNameToUpperCase(List<Account> result) {
		for(Account account:result) {
			String uppName =account.getName().toUpperCase();
			account.setName(uppName);
		}
	}

	@Before("com.nitesh.aopDemo.aspect.AopExpressions.forDaoPackageNotGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		myLogger.info("=== : MyDemoLoggingAspect -> beforeAddAccountAdvice");
		
		MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
		
		myLogger.info("Signature  ==> "+methodSignature );
		
		Object[] args = theJoinPoint.getArgs();
		
		for(Object temp : args) {
			myLogger.info("args : "+temp);
			
			if(temp instanceof Account ) {
				Account account = (Account) temp;
				
				myLogger.info("account name : "+account.getName());
				myLogger.info("account level : "+account.getLevel());
			}
		}
		
		
	}
	
	

	
}
