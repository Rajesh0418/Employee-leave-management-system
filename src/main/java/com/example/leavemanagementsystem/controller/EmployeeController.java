package com.example.leavemanagementsystem.controller;


import com.example.leavemanagementsystem.model.Employee;
import com.example.leavemanagementsystem.model.LeaveRequest;
import com.example.leavemanagementsystem.model.Manager;
import com.example.leavemanagementsystem.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@RequestMapping("/employee")
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentController departmentController;

    @Autowired
    LeaveController leaveController;

    @Autowired
    ManagerController managerController;


    // login auth of employee
    @GetMapping("/login")
    public String logincheck(@ModelAttribute Employee employeeData, HttpSession session) {
        employeeService.findEmployee(employeeData);
        Employee employee = employeeService.getCurrentEmployee();
        if (employee != null) {
            //model.addAttribute("employeeData", employee);  // goes into session
            session.setAttribute("employeeData", employee);
            return "/employee/employeeProfile";
        }
        // if user enters wrong credentials
        return "redirect:/employeeLogin.html?error=Invalid credentials";
    }

    // saving the data of an employee when emp singing up
    @PostMapping("/add-employee")
    public String addEmployee(@ModelAttribute Employee userdata,String managerId) {
        String departmentName = userdata.getDepartment();
        // updating the no. of employees in the dept by deptName
        departmentController.updateNoOfEmployeesByDepartment(departmentName);
        //setting the manager id for emp class
        userdata.setManager(managerController.getManagerById(Integer.parseInt(managerId)));
        employeeService.addEmployee(userdata);
        return "redirect:/employeeLogin.html";
    }

    // for displaying the no. of emp's in the company
    @GetMapping("/employee-count")
    public int getEmployeeCount(){
        return employeeService.getEmployeeCount();
    }

    // getting emp's list
    @GetMapping("/employees")
    public String getEmployeeList(Model model, HttpSession session) {
        List<Employee> employeeList = employeeService.getEmployeeList();
        model.addAttribute("employees", employeeList);
        return "/employee/employeeList"; // your JSP name
    }

    // emp profile
    @GetMapping("/employee-profile")
    public String getEmployeeProfile(Model model, HttpSession session) {
        List<LeaveRequest> leaveRequests = employeeService.getCurrentEmployee().getLeaveRequests();
        session.setAttribute("leaveRequests", leaveRequests);
        return "/employee/employeeProfile";
    }

    // navigating to changing the password page
    @GetMapping("/change-password")
    public String changePassword() {
        return "/employee/empChangePassword";
    }

    // emp logout
    @GetMapping("/logout")
    public String logout(SessionStatus status, Model model, HttpSession session) {
        status.setComplete();
        session.invalidate();
        return "redirect:/employeeLogin.html";
    }

    // Business logic for changing the data
    @PostMapping("/new-password")
    public String setNewPassword(String password, HttpSession session) {
        employeeService.setNewPassword(password);
        Employee employee = employeeService.getCurrentEmployee();
        session.setAttribute("employeeData", employee);
        return "/employee/employeeProfile";
    }

    // navigating to employee login page
    @GetMapping("/employee-login")
    public String employeeLogin() {
        return "redirect:/employeeLogin.html";
    }


    // navigating to emp leave request page
    @GetMapping("/employee-leave-request-status")
    public String employeeLeaveRequestStatus(HttpSession session) {
        Employee emp = employeeService.getCurrentEmployee();
        if (emp == null) {
            return "redirect:/employee-login";
        }
        // setting all leave requests which are in the name of emp
        emp.setLeaveRequests(leaveController.getLeaveRequestsByEmployeeId(emp.getId()));
        List<LeaveRequest> leaveRequestsList = emp.getLeaveRequests();
        session.setAttribute("employeeData", emp);
        session.setAttribute("leaveRequests", leaveRequestsList);
        return "/employee/employeeLeaveRequestStatus";
    }

    // navigating to emp leave request form page if employee wants to add a leave request
    @GetMapping("/leave-request-form")
    public String leaveRequest(HttpSession session) {
        return "/leaveRequest/requestLeave";
    }

    // business logic for sending request to employee and saving the leave request
    @PostMapping("/submit-leave-request")
    public String submitLeaveRequest(LeaveRequest leaveRequest, HttpSession session) {
        Employee employee=employeeService.getCurrentEmployee();
        leaveRequest.setEmployee(employee);
        leaveRequest.setManager(employee.getManager());
        leaveRequest.setStatus("Pending");
        leaveController.addLeaveRequest(leaveRequest);

        Employee employee1 = employeeService.getCurrentEmployee();
        employee.setLeaveRequests(leaveController.getLeaveRequestsList());

        List<LeaveRequest> leaveRequestsList = employee1.getLeaveRequests();
        session.setAttribute("employeeData", employee1);
        session.setAttribute("leaveRequests", leaveRequestsList);
        return "/employee/employeeLeaveRequestStatus";
    }

    // signup page
    @GetMapping("/signup")
    public String signup(HttpSession session) {
        List<Manager> managers=managerController.getManagers();
        session.setAttribute("managers", managers);
        return "employee/employeeSignup";
    }
}

