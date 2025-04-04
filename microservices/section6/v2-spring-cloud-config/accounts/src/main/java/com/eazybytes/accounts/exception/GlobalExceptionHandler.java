package com.eazybytes.accounts.exception;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
   public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException
           (CustomerAlreadyExistsException customerAlreadyExistsException, WebRequest webRequest) {

       ErrorResponseDto errorResponseDto= new ErrorResponseDto
               (webRequest.getDescription(false)
               , HttpStatus.BAD_REQUEST
                       ,customerAlreadyExistsException.getMessage()
                       , LocalDateTime.now());
    return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
   }

    @ExceptionHandler(ResourceNotFindException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException
            (ResourceNotFindException resourceNotFindException, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto= new ErrorResponseDto
                (webRequest.getDescription(false)
                        , HttpStatus.NOT_FOUND
                        ,resourceNotFindException.getMessage()
                        , LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalExceptions(Exception exception, WebRequest webRequest)
            {

        ErrorResponseDto errorResponseDto= new ErrorResponseDto
                (webRequest.getDescription(false)
                        , HttpStatus.INTERNAL_SERVER_ERROR
                        ,exception.getMessage()
                        , LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String > validationErrors= new HashMap<>();
        List<ObjectError> validationErrorList= ex.getBindingResult().getAllErrors();
        validationErrorList.forEach((error) -> {
            String filedName= ((FieldError)error).getField();
            String message= error.getDefaultMessage();
            validationErrors.put(filedName, message);

        });
        return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
    }
}
