package com.capstone.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name="categories")
public class Category extends BaseModel implements Serializable {
    String title;
}
