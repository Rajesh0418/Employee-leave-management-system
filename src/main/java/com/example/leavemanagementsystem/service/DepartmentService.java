package com.example.leavemanagementsystem.service;

import com.example.leavemanagementsystem.model.Department;
import com.example.leavemanagementsystem.repository.DepartmentRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository deptRepo;

    @Getter
    Department department;

    // getting all dept's details
    public List<Department> findAllDepartments() {
        return deptRepo.findAll();
    }

    // getting no. of depts in the company
    public int getDepartmentCount() {
        return deptRepo.findAll().size();
    }

    // getting how many emp's are there in each dept
    public int getNoOfEmployeesByDepartment() {
        return department.getNo_of_employees();
    }

    // getting current dept
    public void getDepartment(String departmentName) {
        department = deptRepo.findAll().stream()
                .filter(dept -> (dept.getDepartment_name().equalsIgnoreCase(departmentName)
                ))
                .findFirst()
                .orElse(null);
    }

    // updating the no. of employees in the dept by 1 and save the updated data
    public void updateNoOfEmployeesByDepartment(int countEmployee) {
        department.setNo_of_employees(countEmployee+1);
        deptRepo.save(department);
    }
}
