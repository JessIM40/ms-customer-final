package com.bootcamp.mscustomer.service;

import com.bootcamp.mscustomer.model.entity.Customer;

import java.util.List;
import java.util.UUID;


public interface CustomerService {

    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(UUID id);
    Customer updateCustomer(UUID id, Customer customerDetails);
    void deleteCustomer(UUID id);
}
