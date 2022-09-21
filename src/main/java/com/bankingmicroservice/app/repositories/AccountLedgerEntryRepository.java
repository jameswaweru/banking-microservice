package com.bankingmicroservice.app.repositories;

import com.bankingmicroservice.app.model.AccountLedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountLedgerEntryRepository extends JpaRepository<AccountLedgerEntry, Long> {

}
