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

    public List<Department> findAllDepartments() {
        return deptRepo.findAll();
    }

    public int getDepartmentCount() {
        return deptRepo.findAll().size();
    }

    public int getNoOfEmployeesByDepartment() {
        return department.getNo_of_employees();
    }

    public void getDepartment(String departmentName) {
        department = deptRepo.findAll().stream()
                .filter(dept -> (dept.getDepartment_name().equalsIgnoreCase(departmentName)
                ))
                .findFirst()
                .orElse(null);
    }

    public void updateNoOfEmployeesByDepartment(int countEmployee) {
        department.setNo_of_employees(countEmployee+1);
        deptRepo.save(department);
    }
}
