package com.capstone.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponseDto {
    private Long productId;
    private String name;
    private String description;
    private Double price;
}
