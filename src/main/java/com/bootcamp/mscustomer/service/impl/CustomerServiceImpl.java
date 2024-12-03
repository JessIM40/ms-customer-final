package com.bootcamp.mscustomer.service.impl;

import com.bootcamp.mscustomer.model.entity.Customer;
import com.bootcamp.mscustomer.repository.CustomerRepository;
import com.bootcamp.mscustomer.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsByDni(customer.getDni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }
        // ID generado si es null o ignorado si se envia en el Request Body
        if (customer.getId() == null) {
            customer.setId(UUID.randomUUID());
        } else {
            customer.setId(UUID.randomUUID()); // Ignora ID, genera uno nuevo
        }
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    @Override
    public Customer getCustomerById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no entoncrado"));
    }


    @Override
    public Customer updateCustomer(UUID id, Customer customerDetails) {
        // Verifica si el cliente existe antes de actualizar
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente con ID: " + id + " no encontrado"));

        // Actualiza los detalles del cliente
        customer.setFirstname(customerDetails.getFirstname());
        customer.setLastname(customerDetails.getLastname());
        customer.setDni(customerDetails.getDni());
        customer.setEmail(customerDetails.getEmail());

        return customerRepository.save(customer);
    }


    @Override
    public void deleteCustomer(UUID id) {
        // Verifica si el cliente existe y si tiene cuentas activas antes de eliminar
        customerRepository.findById(id)
                .ifPresentOrElse(
                        customer -> {
                            if (customerHasActiveAccounts(customer)) {
                                throw new RuntimeException("No se puede eliminar clientes con cuentas activas");
                            }
                            customerRepository.delete(customer);
                        },
                        () -> {
                            throw new RuntimeException("No se encontró al cliente con ID: " + id);
                        }
                );
    }


    private boolean customerHasActiveAccounts(Customer customer) {
        return false;
        // return accountService.hasActiveAccounts(customer.getId());
    }

}

