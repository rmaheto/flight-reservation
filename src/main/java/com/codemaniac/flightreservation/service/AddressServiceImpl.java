package com.codemaniac.flightreservation.service;

import com.codemaniac.flightreservation.entity.Address;
import com.codemaniac.flightreservation.entity.Customer;
import com.codemaniac.flightreservation.repository.AddressRepository;
import com.codemaniac.flightreservation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.MessageProperties;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final MessagingService emailService;

    @Override
    public void updateAddress(Long customerId, Address address) {
        Customer customer = customerRepository.findById (customerId)
                .orElseThrow (()->new RuntimeException ("Customer not found"));

        Address updatedAddress = customer.getAddress();
        updatedAddress.setCity (address.getCity ());
        updatedAddress.setStreet (address.getStreet ());
        updatedAddress.setState (address.getState ());
        updatedAddress.setPostalCode (address.getPostalCode ());
        updatedAddress.setCountry (address.getCountry ());
        addressRepository.save (updatedAddress);
        MessageProperties msg = new MessageProperties ();
        msg.setReceivers (Arrays.asList (customer.getPhoneNumber ()));
        msg.setBody (String.format("Dear %s %s, Your address has been updated ",customer.getFirstName (),
                customer.getLastName ()));
        emailService.sendSms (msg);
    }
}

