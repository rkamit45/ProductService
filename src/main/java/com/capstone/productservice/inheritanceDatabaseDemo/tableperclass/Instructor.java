package com.capstone.productservice.inheritanceDatabaseDemo.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="msc_Instructors")
public class Instructor extends User{
    String subject;
    Integer noOfSessions;
}
