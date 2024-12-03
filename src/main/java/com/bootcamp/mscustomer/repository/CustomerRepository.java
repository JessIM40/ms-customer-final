package com.bootcamp.mscustomer.repository;

import com.bootcamp.mscustomer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean existsByDni(String dni);
}
