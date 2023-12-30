package com.nitesh.product.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nitesh.product.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;
	
	
	
	@Override
	public User findUserByUserName(String userName) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<User> query = session.createQuery("from User where userName=:uName",User.class);
		query.setParameter("uName", userName);
		
		User user = null;
		try {
			user = query.getSingleResult();	
		} catch (Exception e) {
			user = null;
		}
		return user;
	}



	@Override
	public void saveUser(User user) {
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(user);
		
	}
	
	

}
