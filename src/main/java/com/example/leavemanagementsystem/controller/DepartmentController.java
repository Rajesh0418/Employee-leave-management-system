package com.example.leavemanagementsystem.controller;

import com.example.leavemanagementsystem.model.Department;
import com.example.leavemanagementsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("/department")
@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService; 

    //fetching the all departments and setting a session for department
    @GetMapping("/departments")
    public String departments(Model model) {
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("departments", departments);
        return "/department/department";
    }

    //counting the no. of dept's for displaying on manager dashboard
    @GetMapping("/department-count")
    public int getDepartmentCount() {
        return departmentService.getDepartmentCount();
    }


    @GetMapping("/department")
    public void getDepartment(String departmentName) {
        departmentService.getDepartment(departmentName);
    }

    //getting no. of employees from dept for updating the no. of employees in the dept when employee signing up
    @GetMapping("/noOf-employees-department")
    public int getNoOfEmployeesByDepartment(String departmentName) {
        getDepartment(departmentName);
        return departmentService.getNoOfEmployeesByDepartment();
    }

    //for updating the no. of employees in the dept when employee signing up
    public void updateNoOfEmployeesByDepartment(String departmentName) {
        int countEmployee=getNoOfEmployeesByDepartment(departmentName);
        departmentService.updateNoOfEmployeesByDepartment(countEmployee);
    }
}
