package com.nitesh.springboot.thymleafdemo.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nitesh.springboot.thymleafdemo.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Role findRoleByName(String theRoleName) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Role> query = session.createQuery("from role where name=:rolename",Role.class);
		
		query.setParameter("rolename", theRoleName);
		
		Role role = null;
		try {
			role = query.getSingleResult();
		} catch (Exception e) {
			role = null;
		}
		
		return role;
	}

}
