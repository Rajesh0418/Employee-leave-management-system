package com.example.leavemanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private LocalDate startDate;

    @Getter @Setter
    private LocalDate endDate;

    @Getter @Setter
    private String reason;

    @Getter @Setter
    private String status; // PENDING, APPROVED, REJECTED

    // LeaveRequest -> Employee
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @Getter @Setter
    private Employee employee;

    // LeaveRequest -> Manager
    @ManyToOne
    @JoinColumn(name = "manager_id")
    @Getter @Setter
    private Manager manager;
}
