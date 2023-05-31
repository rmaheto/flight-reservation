package com.codemaniac.flightreservation.controller;

import com.codemaniac.flightreservation.entity.Customer;
import com.codemaniac.flightreservation.service.CustomerService;
import com.codemaniac.flightreservation.service.EmailService;
import lombok.AllArgsConstructor;
import org.openapitools.model.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer( @RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer (customer);
        return new ResponseEntity<> (savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerId(@PathVariable("id") Long customerId) {
        Customer customer = customerService.getCustomerById (customerId);
        return new ResponseEntity<> (customer, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers ();
        return new ResponseEntity<> (customers, HttpStatus.OK);
    }

    @PutMapping("{id}")

    public ResponseEntity<Customer> updatedCustomer(@PathVariable("id") Long id,
                                                    @RequestBody Customer customer) {
        customer.setId (id);
        Customer updatedCustomer = customerService.updateCustomer (customer);
        return new ResponseEntity<> (updatedCustomer, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long customerId) {
        customerService.deleteCustomer (customerId);
        return new ResponseEntity<> ("Customer successfully deleted!", HttpStatus.OK);

    }
}