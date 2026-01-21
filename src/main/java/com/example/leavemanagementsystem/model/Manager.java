package com.example.leavemanagementsystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Manager extends User {

    // Manager -> Employees
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<Employee> teamMembers;

    // Manager -> Leave Requests
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<LeaveRequest> handledRequests;
}
