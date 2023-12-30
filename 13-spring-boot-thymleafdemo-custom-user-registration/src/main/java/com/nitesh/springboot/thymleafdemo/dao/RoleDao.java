package com.nitesh.springboot.thymleafdemo.dao;

import com.nitesh.springboot.thymleafdemo.entity.Role;

public interface RoleDao {
	public Role findRoleByName(String theRoleName);
}
