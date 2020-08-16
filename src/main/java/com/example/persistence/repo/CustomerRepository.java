package com.example.persistence.repo;

import com.example.persistence.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repo to store customers
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
