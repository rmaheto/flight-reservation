package com.codemaniac.flightreservation.service;

import com.codemaniac.flightreservation.entity.Customer;
import com.codemaniac.flightreservation.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {

        return customerRepository.save (customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById (customerId);

        return optionalCustomer.get ();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer existingCustomer = customerRepository.findById (customer.getId ()).get();
        existingCustomer.setDateOfBirth (customer.getDateOfBirth ());
        existingCustomer.setEmail (customer.getEmail ());
        existingCustomer.setNationality (customer.getNationality ());
        existingCustomer.setFirstName (customer.getFirstName ());
        existingCustomer.setOtherName (customer.getOtherName ());
        existingCustomer.setLastName (customer.getLastName ());
        existingCustomer.setPassportNumber (customer.getPassportNumber ());
        existingCustomer.setFrequentFlyerNumber (customer.getFrequentFlyerNumber ());
        existingCustomer.setPhoneNumber (customer.getPhoneNumber ());
        Customer updatedCustomer = customerRepository.save(existingCustomer);


        return updatedCustomer;
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById (customerId);

    }
}
