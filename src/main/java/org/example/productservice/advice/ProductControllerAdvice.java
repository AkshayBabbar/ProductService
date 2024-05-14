package org.example.productservice.advice;

import org.example.productservice.dto.ExceptionDTO;
import org.example.productservice.dto.ProductNotFoundExceptionDTO;
import org.example.productservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDTO> handleArithmeticException() {
        ExceptionDTO exceptionDto = new ExceptionDTO();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setStatus("Error");

        ResponseEntity<ExceptionDTO> responseEntity = new ResponseEntity<>(exceptionDto,
                HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBoundException() {
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ProductNotFoundExceptionDTO> handleProductNotFoundException(ProductNotFoundException exception) {
        ProductNotFoundExceptionDTO productNotFoundExceptionDTO = new ProductNotFoundExceptionDTO();
        productNotFoundExceptionDTO.setMessage("Product with " + exception.getMessage() + " was found.");
        return  new ResponseEntity<>(productNotFoundExceptionDTO,HttpStatus.NOT_FOUND);
    }
}
