package com.bankingmicroservice.app.dto.payload;

import lombok.Data;

@Data
public class RegisterCustomer {
    String customerFullName;
    String customerIdNumber;
}
