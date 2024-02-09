package org.example.productservice.advice;

import org.example.productservice.dto.ExceptionDto;
import org.example.productservice.exception.NoProductFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(NoProductFoundException.class)
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(NoProductFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("Failure");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
