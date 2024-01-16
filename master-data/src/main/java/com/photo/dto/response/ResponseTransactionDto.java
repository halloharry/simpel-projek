package com.photo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTransactionDto {

    private String accountName;
    private Integer balance;
    private String accountNumber;
    private String email;
    private Integer amount;

}
