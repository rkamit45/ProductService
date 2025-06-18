package com.capstone.productservice.inheritanceDatabaseDemo.tableperclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name="tpc_users")
@Table(name="tpc_users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    String name;
    String email;
    String password;
}
