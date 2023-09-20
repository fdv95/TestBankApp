package com.example.testbankapp.dto;

import com.example.testbankapp.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDto {

    @Size(min = 4, max = 4)
    String pinCode; // todo захэшировать???
    UserDto userDto;
}
