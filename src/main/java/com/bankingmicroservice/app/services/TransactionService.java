package com.bankingmicroservice.app.services;

import com.bankingmicroservice.app.constants.StatusCodes;
import com.bankingmicroservice.app.dto.responses.TransactionResponse;
import com.bankingmicroservice.app.enums.LedgerEntryType;
import com.bankingmicroservice.app.model.AccountLedgerEntry;
import com.bankingmicroservice.app.model.CustomerAccount;
import com.bankingmicroservice.app.repositories.AccountLedgerEntryRepository;
import com.bankingmicroservice.app.repositories.CustomerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    AccountLedgerEntryRepository accountLedgerEntryRepository;

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    public TransactionResponse triggerTransaction(String accountNumber , double amount , LedgerEntryType ledgerEntryType){
        //get account details
        CustomerAccount customerAccount = customerAccountRepository.getCustomerAccountByAccountNumber(accountNumber);

        if(customerAccount == null){
            return TransactionResponse.builder()
                    .currentBalance(0)
                    .statusCode(StatusCodes.FAILED)
                    .statusMessage("Account not found")
                    .build();
        }

        if(ledgerEntryType.equals(LedgerEntryType.CREDIT)){ //deposit transaction
            customerAccount.setCurrentBalance(customerAccount.getCurrentBalance() + amount);
            customerAccountRepository.save(customerAccount);
            return TransactionResponse.builder()
                    .currentBalance(customerAccount.getCurrentBalance())
                    .statusCode(StatusCodes.SUCCESS)
                    .statusMessage("Amount successfully deposited")
                    .build();
        }

        if(ledgerEntryType.equals(LedgerEntryType.DEBIT)){ //withdrawl transaction

            if(customerAccount.getCurrentBalance() < amount){
                return TransactionResponse.builder()
                        .currentBalance(customerAccount.getCurrentBalance())
                        .statusCode(StatusCodes.FAILED)
                        .statusMessage("Failed. Account has insufficient funds")
                        .build();
            }

            customerAccount.setCurrentBalance(customerAccount.getCurrentBalance() - amount);
            customerAccountRepository.save(customerAccount);
            return TransactionResponse.builder()
                    .currentBalance(customerAccount.getCurrentBalance())
                    .statusCode(StatusCodes.SUCCESS)
                    .statusMessage("Amount successfully withdrawn")
                    .build();
        }

        return TransactionResponse.builder()
                .currentBalance(customerAccount.getCurrentBalance())
                .statusCode(StatusCodes.FAILED)
                .statusMessage("Please specify transaction type")
                .build();

    }


}
