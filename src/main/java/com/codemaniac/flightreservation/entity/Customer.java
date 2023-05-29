package com.codemaniac.flightreservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false)
    private  String firstName;
    @Column(nullable = false)

    private String otherName;
    @Column(nullable = false)

    private String lastName;
    @Column(nullable=false,unique=true)

    private String email;
    @Size (min=10, message="phone number  should have 10 digital")
    private String phoneNumber;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private String nationality;

    @NotNull
    @Size(min=7, message="Passport should have at least 2 characters")
    private String passportNumber;
    @NotNull
    private String frequentFlyerNumber;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;


}
