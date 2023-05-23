package com.codemaniac.flightreservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
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
    public String firstName;
    @Column(nullable = false)

    public String otherName;
    @Column(nullable = false)

    public String lastName;
    @Column(nullable=false,unique=true)

    public String email;
    @Size (min=10, message="phone number  should have 10 digital")
    public Long phoneNumber;
    @NotNull
    Date dateOfBirth;
    @NotNull
    public String nationality;

    @NotNull
    @Size(min=7, message="Passport should have atleast 2 characters")
    public String passportNumber;
    @NotNull

    public String frequentFlyerNumber;

}
