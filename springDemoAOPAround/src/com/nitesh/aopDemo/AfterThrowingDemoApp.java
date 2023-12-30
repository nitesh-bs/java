package com.nitesh.aopDemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nitesh.aopDemo.dao.AccountDAO;
import com.nitesh.aopDemo.dao.MembershipDAO;

public class AfterThrowingDemoApp {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		AccountDAO accountDAO = context.getBean("accountDAO",AccountDAO.class);

		List<Account> accounts = null;
		try {
			
			boolean tripWire = true;
		 	accounts = accountDAO.findAccounts(tripWire);
			
		} catch (Exception e) {
			System.out.println("Main Program Exception : "+e.getMessage());
		}

		System.out.println("accounts : "+accounts);
		
		context.close();
	}
}
