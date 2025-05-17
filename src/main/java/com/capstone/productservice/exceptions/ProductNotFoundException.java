package com.capstone.productservice.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends Exception {
    private Long productId;
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long productId, String message) {
        super(message);
        this.productId = productId;
    }
}
