package com.capstone.productservice.inheritanceDatabaseDemo.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tpc_mentors")
@Table(name="tpc_mentors")
public class Mentor extends User {
    Double avgRating;
    String company;
}
