package com.springrest.MVCTest.repositories;

import com.springrest.MVCTest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
