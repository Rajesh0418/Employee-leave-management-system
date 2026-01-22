package com.example.leavemanagementsystem.service;

import com.example.leavemanagementsystem.model.Employee;
import com.example.leavemanagementsystem.model.LeaveRequest;
import com.example.leavemanagementsystem.repository.DepartmentRepository;
import com.example.leavemanagementsystem.repository.EmployeeRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repo;

    @Autowired
    DepartmentRepository deptRepo;

    @Getter
    Employee currentEmployee;

    @Getter
    List<LeaveRequest> leaveRequests;

    // getting emp data
    public void findEmployee(Employee userdata) {
        currentEmployee = repo.findAll().stream()
                .filter(user -> (user.getEmail().equalsIgnoreCase(userdata.getEmail()) &&
                        user.getPassword().equalsIgnoreCase(userdata.getPassword())
                ))
                .findFirst()
                .orElse(null);
    }


    // for to display the no. of emp's in manager dashboard
    public int getEmployeeCount() {
        return repo.findAll().size();
    }

    // list of employees data
    public List<Employee> getEmployeeList() {
        return repo.findAll();
    }

    // saving the new emp data
    public void addEmployee(Employee userdata) {
        repo.save(userdata);
    }

    // setting new password
    public void setNewPassword(String password) {
        currentEmployee.setPassword(password);
        repo.save(currentEmployee);
    }
}
