package com.bootcamp.mscustomer.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String firstname;

    @NotBlank
    @Column(nullable = false)
    private String lastname;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String dni;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    public Customer(UUID id, String firstname, String lastname, String dni, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dni = dni;
        this.email = email;
    }
}
