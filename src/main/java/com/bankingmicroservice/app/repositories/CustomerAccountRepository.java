package com.bankingmicroservice.app.repositories;

import com.bankingmicroservice.app.model.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount , Long> {

    CustomerAccount getCustomerAccountByAccountNumber(String accountNumber);

}
