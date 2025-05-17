package com.capstone.productservice.dtos;

import com.capstone.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDto {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Category category;
}
