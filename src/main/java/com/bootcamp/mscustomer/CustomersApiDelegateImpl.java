package com.bootcamp.mscustomer;

import com.bootcamp.mscustomer.api.CustomersApiDelegate;
import com.bootcamp.mscustomer.model.CustomersRequest;
import com.bootcamp.mscustomer.model.CustomersResponse;
import com.bootcamp.mscustomer.model.entity.Customer;
import com.bootcamp.mscustomer.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomersApiDelegateImpl implements CustomersApiDelegate {

    @Autowired
    private CustomerService customerService;

    @Override
    public ResponseEntity<CustomersResponse> createCustomer(CustomersRequest customersRequest) {
        Customer customer = new Customer();
        customer.setFirstname(customersRequest.getFirstname());
        customer.setLastname(customersRequest.getLastname());
        customer.setDni(customersRequest.getDni());
        customer.setEmail(customersRequest.getEmail());

        Customer createdCustomer = customerService.createCustomer(customer);
        CustomersResponse response = new CustomersResponse();
        response.setId(createdCustomer.getId());
        response.setFirstname(createdCustomer.getFirstname());
        response.setLastname(createdCustomer.getLastname());
        response.setDni(createdCustomer.getDni());
        response.setEmail(createdCustomer.getEmail());

        return ResponseEntity.status(201).body(response);

    }
}
