package com.capstone.productservice.inheritanceDatabaseDemo.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="msc_TA")
public class TA extends User{
    Double avgRating;
    Integer noOfHr;
}
