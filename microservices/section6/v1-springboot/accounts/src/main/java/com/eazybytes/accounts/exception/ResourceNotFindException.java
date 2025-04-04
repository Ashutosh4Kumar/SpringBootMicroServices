package com.eazybytes.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFindException extends RuntimeException {

    public ResourceNotFindException(String resourceName, String filedName, String fieldValue) {
        super(String.format("%s not found with the given data %s : %s",resourceName, filedName, fieldValue));
    }

}
