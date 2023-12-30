package com.nitesh.product.dao;

import com.nitesh.product.entity.Role;

public interface RoleDao {

	public Role findRoleByRoleName(String roleName);
}
