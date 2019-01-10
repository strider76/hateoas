package com.at.tutorialrest.repository;

import com.at.tutorialrest.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
