package com.capstone.productservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public abstract class BaseModel {
    long id;
    Date createdAt;
    Date updatedAt;
}
