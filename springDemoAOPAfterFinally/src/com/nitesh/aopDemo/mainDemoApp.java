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
		
		Account account= new Account();
		account.setName("nitesh");
		account.setLevel("Gold");
		accountDAO.addAccount(account,false);
		accountDAO.accountDetails();
		
		accountDAO.setName("foobar");
		accountDAO.setServiceCode("silver");
		
		System.out.println("Name : "+accountDAO.getName() + " ::: "+" Code : "+accountDAO.getServiceCode());
		
		membershipDAO.addMemberAccount();
		context.close();
	}
}
