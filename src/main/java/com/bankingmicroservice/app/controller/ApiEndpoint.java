package com.bankingmicroservice.app.controller;

import com.bankingmicroservice.app.constants.EndPoints;
import com.bankingmicroservice.app.dto.payload.RegisterCustomer;
import com.bankingmicroservice.app.dto.responses.RegisterCustomerResponse;
import com.bankingmicroservice.app.dto.responses.TransactionResponse;
import com.bankingmicroservice.app.enums.CardTypes;
import com.bankingmicroservice.app.enums.LedgerEntryType;
import com.bankingmicroservice.app.services.CustomersService;
import com.bankingmicroservice.app.services.TransactionService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiEndpoint {

    @Autowired
    CustomersService customersService;

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = EndPoints.REGISTER_CUSTOMER)
    public RegisterCustomerResponse registerCustomer(@RequestBody RegisterCustomer registerCustomer){
        return customersService.createCustomer(registerCustomer.getCustomerIdNumber(), registerCustomer.getCustomerFullName());
    }

    @GetMapping(value = EndPoints.CREATE_CUSTOMER_BANK_ACCOUNT)
    public RegisterCustomerResponse createBankAccount(@PathVariable String customerIdNumber){
        return customersService.createCustomerBankAccount(customerIdNumber);
    }

    @GetMapping(value = EndPoints.ASSIGN_CUSTOMER_CARD)
    public RegisterCustomerResponse assignCard(@PathVariable String customerIdNumber, @PathVariable String customerAccountNumber, @PathVariable CardTypes cardType){
        return customersService.assignCustomerACard(customerIdNumber, customerAccountNumber, cardType);
    }

    @GetMapping(value = EndPoints.TRIGGER_TRANSACTION)
    public TransactionResponse triggerTransaction(@PathVariable String accountNumber, @PathVariable double amount, @PathVariable LedgerEntryType ledgerEntryType){
        return transactionService.triggerTransaction(accountNumber, amount, ledgerEntryType);
    }

    @GetMapping(value = EndPoints.GET_CUSTOMER_DETAILS)
    public RegisterCustomerResponse getCustomerDetails(@PathVariable String customerIdNumber){
        return customersService.getCustomerDetails(customerIdNumber);
    }



}
