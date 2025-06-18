package com.capstone.productservice.inheritanceDatabaseDemo.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="msc_Mentors")
public class Mentor extends User{
    Double avgRating;
    String company;
}
