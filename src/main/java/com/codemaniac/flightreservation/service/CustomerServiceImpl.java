package com.codemaniac.flightreservation.service;
import com.codemaniac.flightreservation.entity.Address;
import com.codemaniac.flightreservation.entity.Customer;
import com.codemaniac.flightreservation.exception.CustomerNotFoundException;
import com.codemaniac.flightreservation.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.openapitools.model.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final EmailService emailService;

    @Override
    public Customer createCustomer(Customer customer){

        Customer savedCustomer =  customerRepository.save(customer);
        MessageDTO messageDTO = new MessageDTO ();
        messageDTO.setReceivers (Arrays.asList (customer.getEmail ()));
        messageDTO.setSubject ("Welcome To Delta Airlines");
        String messageBody = String.format ("Thank you %s %s for Flying with Delta Airlines",customer.getFirstName (),
                customer.getLastName ());
        messageDTO.setBody (messageBody);
        emailService.sendEmail (messageDTO);
        return savedCustomer;
    }

    @Override
    public Customer getCustomerById(Long customerId){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        // Check if the customer exists
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            // Throw an exception or handle the case when the customer does not exist
            throw new CustomerNotFoundException ("Customer not found for ID: " + customerId);
        }
    }

    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> optionalExistingCustomer = customerRepository.findById(customer.getId());
        // Check if the customer exists
        if (optionalExistingCustomer.isPresent()) {
            Customer existingCustomer = optionalExistingCustomer.get();
            existingCustomer.setDateOfBirth(customer.getDateOfBirth());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setNationality(customer.getNationality());
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setOtherName(customer.getOtherName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setPassportNumber(customer.getPassportNumber());
            existingCustomer.setFrequentFlyerNumber(customer.getFrequentFlyerNumber());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            Address updatedAddress = customer.getAddress();
            existingCustomer.setAddress(updatedAddress);
            Customer updatedCustomer =  customerRepository.save(customer);
            MessageDTO messageDTO = new MessageDTO ();
            messageDTO.setReceivers (Arrays.asList (updatedCustomer.getPhoneNumber()));
            String messageBody = String.format ("Dear %s %s, your customer details has been updated",updatedCustomer.getFirstName (),
                    updatedCustomer.getLastName ());
            messageDTO.setBody (messageBody);
            emailService.sendSms(messageDTO);
            return updatedCustomer;
        } else {
            // Throw an exception or handle the case when the customer does not exist
            throw new CustomerNotFoundException("Customer not found for ID: " + customer.getId());
        }
    }


    @Override
    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);
    }
}
