package com.nitesh.aopDemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nitesh.aopDemo.Account;

@Component
public class AccountDAO {

	private String name,serviceCode;
	
	
	public String getName() {
		System.out.println(getClass()+ " ==> getName() ");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass()+ " ==> setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass()+ " ==> getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass()+ " ==> setServiceCode()");
		this.serviceCode = serviceCode;
	}

	public void addAccount(Account account,boolean flag) {
		System.out.println(getClass()+" : AccountDAO -> addAccount()");
	}
	
	public void accountDetails() {
		System.out.println(getClass()+ " === accountDetails()");
	}
	
	public List<Account> findAccounts() {
		
		List<Account> accounts = new ArrayList<>();
		accounts.add(new Account("nitesh", "Gold"));
		accounts.add(new Account("raj", "Silver"));
		accounts.add(new Account("yash", "Platinum"));
		return accounts;
	}
}
