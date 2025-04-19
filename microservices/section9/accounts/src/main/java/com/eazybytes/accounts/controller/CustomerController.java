package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.service.ICustomerService;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

    private final ICustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }



    @GetMapping(value = "/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
            @RequestHeader("eazybank-correlation-id") String correlationId,
            @Pattern(regexp = "(^$|[0-9]{10})",
            message = "Mobile number must be 10 digits") @RequestParam String mobileNumber) {
        logger.debug("eazybank-correlation-id found: " + correlationId);
        CustomerDetailsDto customerDetailsDto= customerService.fetchCustomerDetails(mobileNumber,correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);

    }

}
