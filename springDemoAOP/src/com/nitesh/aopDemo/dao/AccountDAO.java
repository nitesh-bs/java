package com.nitesh.aopDemo.dao;

import org.springframework.stereotype.Component;

import com.nitesh.aopDemo.Account;

@Component
public class AccountDAO {

	public void addAccount(Account account,boolean flag) {
		System.out.println(getClass()+" : AccountDAO -> addAccount");
	}
	
	public void accountDetails() {
		System.out.println(getClass()+ " === accountDetails()");
	}
}
