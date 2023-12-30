package com.nitesh.aopDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nitesh.aopDemo.dao.AccountDAO;
import com.nitesh.aopDemo.dao.MembershipDAO;

public class mainDemoApp {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		AccountDAO accountDAO = context.getBean("accountDAO",AccountDAO.class);

		MembershipDAO membershipDAO = context.getBean("membershipDAO",MembershipDAO.class);
		
		membershipDAO.addMemberAccount();
		
		accountDAO.addAccount(new Account(),false);
		accountDAO.accountDetails();
		
		context.close();
	}
}
