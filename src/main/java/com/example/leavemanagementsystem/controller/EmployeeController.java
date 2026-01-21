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

    @GetMapping("/login")
    public String logincheck(@ModelAttribute Employee employeeData, HttpSession session) {
        employeeService.findEmployee(employeeData);
        Employee employee = employeeService.getCurrentEmployee();
        if (employee != null) {
            //model.addAttribute("employeeData", employee);  // goes into session
            session.setAttribute("employeeData", employee);
            return "/employee/employeeProfile";
        }
        return "redirect:/employeeLogin.html?error=Invalid credentials";
    }

    @PostMapping("/add-employee")
    public String addEmployee(@ModelAttribute Employee userdata,String managerId) {
        String departmentName = userdata.getDepartment();
        departmentController.updateNoOfEmployeesByDepartment(departmentName);
        userdata.setManager(managerController.getManagerById(Integer.parseInt(managerId)));
        employeeService.addEmployee(userdata);
        return "redirect:/employeeLogin.html";
    }

    @GetMapping("/employee-count")
    public int getEmployeeCount(){
        return employeeService.getEmployeeCount();
    }

    @GetMapping("/employees")
    public String getEmployeeList(Model model, HttpSession session) {
        List<Employee> employeeList = employeeService.getEmployeeList();
        model.addAttribute("employees", employeeList);
        return "/employee/employeeList"; // your JSP name
    }

    @GetMapping("/employee-profile")
    public String getEmployeeProfile(Model model, HttpSession session) {
        List<LeaveRequest> leaveRequests = employeeService.getCurrentEmployee().getLeaveRequests();
        session.setAttribute("leaveRequests", leaveRequests);
        return "/employee/employeeProfile";
    }

    @GetMapping("/change-password")
    public String changePassword() {
        return "/employee/empChangePassword";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus status, Model model, HttpSession session) {
        status.setComplete();
        session.invalidate();
        return "redirect:/employeeLogin.html";
    }

    @PostMapping("/new-password")
    public String setNewPassword(String password, HttpSession session) {
        employeeService.setNewPassword(password);
        Employee employee = employeeService.getCurrentEmployee();
        session.setAttribute("employeeData", employee);
        return "/employee/employeeProfile";
    }

    @GetMapping("/employee-login")
    public String employeeLogin() {
        return "redirect:/employeeLogin.html";
    }


    @GetMapping("/employee-leave-request-status")
    public String employeeLeaveRequestStatus(HttpSession session) {
        Employee emp = employeeService.getCurrentEmployee();
        if (emp == null) {
            return "redirect:/employee-login";
        }
        emp.setLeaveRequests(leaveController.getLeaveRequestsByEmployeeId(emp.getId()));
        List<LeaveRequest> leaveRequestsList = emp.getLeaveRequests();
        session.setAttribute("employeeData", emp);
        session.setAttribute("leaveRequests", leaveRequestsList);
        return "/employee/employeeLeaveRequestStatus";
    }

    @GetMapping("/leave-request-form")
    public String leaveRequest(HttpSession session) {
        return "/leaveRequest/requestLeave";
    }

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

    @GetMapping("/signup")
    public String signup(HttpSession session) {
        List<Manager> managers=managerController.getManagers();
        session.setAttribute("managers", managers);
        return "employee/employeeSignup";
    }
}

