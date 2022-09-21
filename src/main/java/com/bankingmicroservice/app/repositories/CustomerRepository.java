package com.bankingmicroservice.app.repositories;

import com.bankingmicroservice.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer getCustomerByIdNumber(String idNumber);

}
