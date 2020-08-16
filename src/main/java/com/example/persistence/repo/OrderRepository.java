package com.example.persistence.repo;

import com.example.persistence.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repo to store orders
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
