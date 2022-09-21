package com.bankingmicroservice.app.repositories;

import com.bankingmicroservice.app.model.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCardRepository extends JpaRepository<CustomerCard , Long> {

}
