package com.capstone.productservice.inheritanceDatabaseDemo.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tpc_instructors")
@Table(name="tpc_instructors")
public class Instructor extends User {
    String subject;
    Integer noOfSessions;
}
