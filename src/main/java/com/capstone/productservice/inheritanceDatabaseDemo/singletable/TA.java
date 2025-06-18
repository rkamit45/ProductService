package com.capstone.productservice.inheritanceDatabaseDemo.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tpc_tas")
@Table(name="tpc_tas")
public class TA extends User {
    Double avgRating;
    Integer noOfHr;
}
