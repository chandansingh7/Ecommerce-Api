package com.example.ecommerceapi.dao;

import com.example.ecommerceapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
