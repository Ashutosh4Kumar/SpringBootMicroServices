package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CustomerDto {
    @NotEmpty(message = "Name can't be null or empty")
    @Size(min = 5,max = 30)
    private String name;

    @Email(message = "Email should be valid value")
    @NotEmpty(message = "Email can't be null or empty")
    private String email;

    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
