package com.codemaniac.flightreservation.controller;

import com.codemaniac.flightreservation.entity.Address;
import com.codemaniac.flightreservation.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PutMapping ("/{customerId}")
    public ResponseEntity<Void> updateAddress(@PathVariable Long customerId, @RequestBody Address address){
        addressService.updateAddress (customerId, address);
        return new ResponseEntity<> (HttpStatus.ACCEPTED);
    }
}
