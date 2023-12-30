package com.nitesh.aopDemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nitesh.aopDemo.dao.AccountDAO;
import com.nitesh.aopDemo.dao.MembershipDAO;

public class AfterReturningDemoApp {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		AccountDAO accountDAO = context.getBean("accountDAO",AccountDAO.class);

		List<Account> accounts = accountDAO.findAccounts(true);

		System.out.println("accounts : "+accounts);
		
		context.close();
	}
}
