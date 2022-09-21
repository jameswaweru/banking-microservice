package com.bankingmicroservice.app.dto.responses;

import com.bankingmicroservice.app.model.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterCustomerResponse {
    private String statusCode;
    private String statusMessage;
    private Customer customer;
}
