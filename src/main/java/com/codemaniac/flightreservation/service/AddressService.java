package com.codemaniac.flightreservation.service;

import com.codemaniac.flightreservation.entity.Address;

public interface AddressService {

    public void updateAddress(Long customerId, Address address);
}
