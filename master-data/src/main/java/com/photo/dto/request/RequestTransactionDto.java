package com.photo.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestTransactionDto {

    private Integer balance;
    private String accountNumber;
}
