package com.capstone.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductNotFoundExceptionDto {
    private Long productId;
    private String message;
    private String resolution;
}

