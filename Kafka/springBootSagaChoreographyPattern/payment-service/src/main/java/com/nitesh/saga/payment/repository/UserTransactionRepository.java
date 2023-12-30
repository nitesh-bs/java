package com.nitesh.saga.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.saga.payment.entity.UserTransaction;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Integer> {

}
