package com.bootcamp.mscustomer.service.impl;

import com.bootcamp.mscustomer.model.CustomersRequest;
import com.bootcamp.mscustomer.model.CustomersResponse;
import com.bootcamp.mscustomer.model.entity.Customer;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {

    public Customer getCustomerEntity(CustomersRequest request) {
        Customer entity = new Customer();
        entity.setFirstname(request.getFirstname());
        entity.setLastname(request.getLastname());
        entity.setDni(request.getDni());
        entity.setEmail(request.getEmail());
        return entity;
    }

    public CustomersResponse getCustomersResponse(Customer entity) {
        CustomersResponse response = new CustomersResponse();
        response.setFirstname(entity.getFirstname());
        response.setLastname(entity.getLastname());
        response.setDni(entity.getDni());
        response.setEmail(entity.getEmail());
        return response;
    }
}
