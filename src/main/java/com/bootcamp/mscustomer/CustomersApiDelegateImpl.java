package com.bootcamp.mscustomer;

import com.bootcamp.mscustomer.api.CustomersApiDelegate;
import com.bootcamp.mscustomer.model.CustomersRequest;
import com.bootcamp.mscustomer.model.CustomersResponse;
import com.bootcamp.mscustomer.model.entity.Customer;
import com.bootcamp.mscustomer.service.CustomerService;

import com.bootcamp.mscustomer.service.impl.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CustomersApiDelegateImpl implements CustomersApiDelegate {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public ResponseEntity<CustomersResponse> createCustomer(CustomersRequest customersRequest) {
        // CustomerMapper convierte CustomerRequest a Customer
        Customer customer = customerMapper.getCustomerEntity(customersRequest);

        Customer createdCustomer = customerService.createCustomer(customer);

        // CustomerMapper convierte Customer a CustomersResponse
        CustomersResponse response = customerMapper.getCustomersResponse(createdCustomer);

        return ResponseEntity.status(201).body(response);
    }


    @Override
    public ResponseEntity<CustomersResponse> updateCustomer(UUID customerId, CustomersRequest customersRequest) {
        // CustomerMapper convierte CustomerRequest a Customer
        Customer customerDetails = customerMapper.getCustomerEntity(customersRequest);

        Customer updatedCustomer = customerService.updateCustomer(customerId, customerDetails);

        // CustomerMapper convierte Customer a CustomersResponse
        CustomersResponse response = customerMapper.getCustomersResponse(updatedCustomer);

        return ResponseEntity.status(201).body(response);
    }

}
