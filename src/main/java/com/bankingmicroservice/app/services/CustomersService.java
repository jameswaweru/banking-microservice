package com.bankingmicroservice.app.services;

import com.bankingmicroservice.app.constants.StatusCodes;
import com.bankingmicroservice.app.dto.responses.RegisterCustomerResponse;
import com.bankingmicroservice.app.enums.CardTypes;
import com.bankingmicroservice.app.model.Customer;
import com.bankingmicroservice.app.model.CustomerAccount;
import com.bankingmicroservice.app.model.CustomerCard;
import com.bankingmicroservice.app.repositories.CustomerAccountRepository;
import com.bankingmicroservice.app.repositories.CustomerCardRepository;
import com.bankingmicroservice.app.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Service to handle customer onboarding , creation of bank accounts and assigning credit cards
 */

@Service
@Slf4j
public class CustomersService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Autowired
    CustomerCardRepository customerCardRepository;

    public RegisterCustomerResponse createCustomer(String idNumber , String fullName){
        //check if customer existed
        Customer fetchCustomerResults = customerRepository.getCustomerByIdNumber(idNumber);

        if(fetchCustomerResults != null){
            return RegisterCustomerResponse.builder()
                    .customer(fetchCustomerResults)
                    .statusCode(StatusCodes.ALREADY_EXISTS)
                    .statusMessage("Customer profile already exidsts")
                    .build();
        }

        Customer registeredCustomer =  customerRepository.save(Customer.builder()
                        .idNumber(idNumber)
                        .customerFullName(fullName)
                .build());

        return RegisterCustomerResponse.builder()
                .statusCode(StatusCodes.SUCCESS)
                .statusMessage("Customer successfully registered")
                .customer(registeredCustomer)
                .build();
    }

    public RegisterCustomerResponse createCustomerBankAccount(String customerIdNumber){

        log.info("About to create bank account for {} ", customerIdNumber);
        //check if customer existed
        Customer fetchCustomerResults = customerRepository.getCustomerByIdNumber(customerIdNumber);
        if(fetchCustomerResults != null){
            log.info("Customer exists ...");
            CustomerAccount customerAccount = customerAccountRepository.save(CustomerAccount.builder()
                    .accountNumber(String.valueOf(generateRandomNumber(111111111, 999999999)))
                    .currentBalance(0)
                    //.customer(fetchCustomerResults)
                    .build());
            log.info("Customer account created ...");

            fetchCustomerResults.customerAccounts.add(customerAccount);
            customerRepository.save(fetchCustomerResults);

            log.info("account mapped to customer ...");
            return RegisterCustomerResponse.builder()
                    .statusCode(StatusCodes.SUCCESS)
                    .statusMessage("Customer account successfully created")
//                    .customer(fetchCustomerResults)
                    .build();
        }

        return RegisterCustomerResponse.builder()
                .statusCode(StatusCodes.FAILED)
                .statusMessage("Customer with id "+customerIdNumber+" not found")
                .build();
    }

    public RegisterCustomerResponse assignCustomerACard(String customerIdNumber , String customerAccountNumber,
                                                        CardTypes cardType){
        //check if customer existed
        Customer fetchCustomerResults = customerRepository.getCustomerByIdNumber(String.valueOf(customerIdNumber));

        if(fetchCustomerResults == null){
            return RegisterCustomerResponse.builder()
                    .statusCode(StatusCodes.FAILED)
                    .statusMessage("Customer with id "+customerIdNumber+" not found")
                    .build();
        }

        //check if bank account exists
        CustomerAccount customerAccount = customerAccountRepository.getCustomerAccountByAccountNumber(customerAccountNumber);
        if(customerAccount == null){
            return RegisterCustomerResponse.builder()
                    .statusCode(StatusCodes.FAILED)
                    .statusMessage("No bank account found with no : "+customerAccountNumber)
                    .build();
        }

        //generateCard
        CustomerCard customerCard = customerCardRepository.save(CustomerCard.builder()
                .cardType(cardType)
                        .cardNumber(String.valueOf(generateRandomNumber(11111111,99999999)))
                        .customerAccountId(customerAccount.getCustomerAccountId())
                        .accountNumber(customerAccountNumber)
                        //.customer(fetchCustomerResults)
                .build());

        fetchCustomerResults.customerCards.add(customerCard);
        customerRepository.save(fetchCustomerResults);

        return RegisterCustomerResponse.builder()
                .statusCode(StatusCodes.SUCCESS)
                .statusMessage("Customer successfully assigned card")
                //.customer(fetchCustomerResults)
                .build();

    }

    public RegisterCustomerResponse getCustomerDetails (String customerIdNumber){
        Customer customer = customerRepository.getCustomerByIdNumber(customerIdNumber);
        if(customer != null){
            return RegisterCustomerResponse.builder()
                    .statusCode(StatusCodes.SUCCESS)
                    .statusMessage("Customer details found")
                    .customer(customer)
                    .build();
        }
        return RegisterCustomerResponse.builder()
                .statusCode(StatusCodes.NOT_FOUND)
                .statusMessage("Customer details found")
                .build();
    }

    private static int generateRandomNumber(int min, int max){
        //generate otp
        Random random = new Random();
        int random_int = random.ints(min, max)
                .findFirst()
                .getAsInt();
        return random_int;
    }



}
