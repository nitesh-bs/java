package com.nitesh.saga.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.saga.payment.entity.UserBalance;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {

}
