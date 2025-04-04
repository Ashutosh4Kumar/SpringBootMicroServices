package com.eazybytes.accounts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;

@Data
public class AccountsDto {
    @NotEmpty(message = "Account number can't be empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    private Long accountNumber;


    @NotEmpty(message = "Account type can't be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch Address type can't be null or empty")
    private String branchAddress;

    private CustomerDto customerDto;
}
