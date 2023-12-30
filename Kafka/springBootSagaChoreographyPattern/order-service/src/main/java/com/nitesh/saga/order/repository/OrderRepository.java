package com.nitesh.saga.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.saga.order.entity.PurchaseOrder;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer>{

}
