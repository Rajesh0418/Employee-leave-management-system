package com.example.leavemanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    protected int id;

    @Getter
    @Setter
    protected String name;
    @Getter
    @Setter
    protected String email;
    @Getter
    @Setter
    protected String password;
    @Getter
    @Setter
    protected String phoneNumber;

    @Getter
    @Setter
    protected String gender;

}
