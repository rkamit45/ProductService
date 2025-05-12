package com.capstone.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseModel {

    String title;
    Double price;
    String imageUrl;
    Category category;
}
