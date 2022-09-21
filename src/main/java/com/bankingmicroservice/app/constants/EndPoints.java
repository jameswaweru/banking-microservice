package com.bankingmicroservice.app.constants;

public class EndPoints {
    public static final String REGISTER_CUSTOMER = "/customer/register";
    public static final String CREATE_CUSTOMER_BANK_ACCOUNT = "/customer/create-bank-acocunt/{customerIdNumber}";
    public static final String ASSIGN_CUSTOMER_CARD = "/customer/assign-card/{customerIdNumber}/customerAccountNumber/{customerAccountNumber}/cardtype/{cardType}";
    public static final String GET_CUSTOMER_DETAILS = "/customer/fetch/{customerIdNumber}";
    public static final String TRIGGER_TRANSACTION = "/transaction/account-number/{accountNumber}/amount/{amount}/ledgerEntryType/{ledgerEntryType}";

}
