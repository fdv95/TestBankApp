package com.example.testbankapp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferDto {
    String pinCode;
    BigDecimal amount;
    Long sourceAccountNumber;
    Long targetAccountNumber;
}
