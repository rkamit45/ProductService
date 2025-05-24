package com.capstone.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Product extends BaseModel {

    String title;
    Double price;
    String description;
    String imageUrl;
    @ManyToOne
    @JoinColumn(name="group_id", referencedColumnName = "id")
    Category category;
}
