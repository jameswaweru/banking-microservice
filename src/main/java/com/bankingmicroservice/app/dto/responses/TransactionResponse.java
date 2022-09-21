package com.bankingmicroservice.app.dto.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {
    private String statusCode;
    private String statusMessage;
    private double currentBalance;
}
