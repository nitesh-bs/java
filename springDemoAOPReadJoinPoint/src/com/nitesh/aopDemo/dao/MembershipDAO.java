package com.nitesh.aopDemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public boolean addMemberAccount() {
		System.out.println(getClass()+" -> MembershipDAO ==>  addAccount");
		return true;
	}
}
