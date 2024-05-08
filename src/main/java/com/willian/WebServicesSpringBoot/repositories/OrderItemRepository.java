package com.willian.WebServicesSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willian.WebServicesSpringBoot.entities.OrderItem;
import com.willian.WebServicesSpringBoot.entities.pk.OrderItemPK;

@Repository
public interface OrderItemRepository  extends JpaRepository<OrderItem, OrderItemPK>{

}
