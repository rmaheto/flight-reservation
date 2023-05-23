package com.codemaniac.flightreservation.repository;

import com.codemaniac.flightreservation.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long> {
//    Map<Object, Object> findAllById(Long customerId);
}
