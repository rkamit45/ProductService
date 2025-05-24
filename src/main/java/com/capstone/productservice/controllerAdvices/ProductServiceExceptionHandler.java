package com.capstone.productservice.controllerAdvices;

import com.capstone.productservice.dtos.ExceptionDto;
import com.capstone.productservice.dtos.ProductNotFoundExceptionDto;
import com.capstone.productservice.exceptions.CategoryNotFoundException;
import com.capstone.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductServiceExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException pnfe){
        ProductNotFoundExceptionDto productNotFoundExceptionDto = new ProductNotFoundExceptionDto();
        // pnfe.getStackTrace();
        productNotFoundExceptionDto.setProductId(pnfe.getProductId());
        productNotFoundExceptionDto.setMessage(pnfe.getMessage());
        productNotFoundExceptionDto.setResolution("Please try again with valid productId");
        return new ResponseEntity<>(productNotFoundExceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntionedException(RuntimeException rte){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(rte.getMessage());
        exceptionDto.setResolutionDetails("You need to more money to get it resolve from us.");
        return new ResponseEntity<>(exceptionDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGenericException(Exception ex){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setResolutionDetails("Something went wrong");
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleCategoryNotFoundException(CategoryNotFoundException cnfe){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(cnfe.getMessage());
        exceptionDto.setResolutionDetails("Please create a new category.");
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
