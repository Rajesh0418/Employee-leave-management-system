package com.example.leavemanagementsystem.controller;


import com.example.leavemanagementsystem.model.LeaveRequest;
import com.example.leavemanagementsystem.model.Manager;
import com.example.leavemanagementsystem.service.EmployeeService;
import com.example.leavemanagementsystem.service.ManagerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/manager") // correct
@Controller
public class ManagerController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentController departmentController;

    @Autowired
    ManagerService managerService;

    @Autowired
    LeaveController leaveController;
    @GetMapping("/login")
    public String logincheck(@ModelAttribute Manager ManagerData, Model model, HttpSession session) {
        managerService.findManager(ManagerData);
        Manager manager = managerService.getCurrentManager();
        if (manager != null) {
            int empCount = employeeService.getEmployeeCount(); // from DB
            int deptCount = departmentController.getDepartmentCount();
            model.addAttribute("empCount", empCount);
            model.addAttribute("deptCount", deptCount);
            model.addAttribute("userdata", manager);  // goes into session
            session.setAttribute("userdata", manager);
            return "/manager/dashboard";

        }
        return "redirect:/ManagerLogin.html?error=Invalid credentials";
    }


    public boolean userLoggedInStatus() {
        return managerService.getCurrentManager()==null;// not logged in
    }

    @GetMapping("/home")
    public String home() {
        if (userLoggedInStatus()) {
            return "redirect:/managerLogin.html"; // not logged in
        }
        return "/manager/dashboard";  // safe to show
    }

    @PostMapping("/add-manager")
    public String addManager(@ModelAttribute Manager userdata) {
        managerService.addManager(userdata);
        return "redirect:/managerLogin.html";
    }

    @GetMapping("/dashboard")
    public String managerDashboard(Model model)
    {
        int empCount = employeeService.getEmployeeCount(); // from DB
        int deptCount= departmentController.getDepartmentCount();
        model.addAttribute("empCount", empCount);
        model.addAttribute("deptCount", deptCount);
        return "/manager/dashboard";
    }

    @GetMapping("/manager-login")
    public String managerLogin() {
        return "redirect:/managerLogin.html";
    }

    @PostMapping("/new-password")
    public String setNewPassword(String password, HttpSession session) {
        managerService.setNewPassword(password);
        Manager employee = managerService.getCurrentManager();
        session.setAttribute("employeeData", employee);
        return "/manager/managerProfile";
    }

    @GetMapping("/change-password")
    public String changePassword() {
        return "/manager/managerChangePassword";
    }

    @GetMapping("/manager-profile")
    public String managerProfile() {
        return "/manager/managerProfile";
    }


    @GetMapping("/employee-leave-list")
    public String employeeLeaveRequestStatus(HttpSession session) {
        Manager manager = managerService.getCurrentManager();
        if (manager == null) {
            return "redirect:/manager/manager-login";
        }
        manager.setHandledRequests(leaveController.getLeaveRequestsByManagerId(manager.getId()));
        List<LeaveRequest> leaveRequestHandlerList = manager.getHandledRequests();
        session.setAttribute("userdata", manager);
        session.setAttribute("leaveRequestsHandler", leaveRequestHandlerList);
        return "/manager/managerLeaveResponseStatus";
    }

    @GetMapping("/leave-request-accepted")
    public String submitLeaveRequestAccepted(@RequestParam int id, HttpSession session) {

        leaveController.setStatusById(id,"Accepted");

        Manager manager = managerService.getCurrentManager();
        manager.setHandledRequests(leaveController.getLeaveRequestsList());

        List<LeaveRequest> leaveRequestsList = manager.getHandledRequests();

        session.setAttribute("userdata", manager);
        session.setAttribute("leaveRequestsHandler", leaveRequestsList);
        return "/manager/managerLeaveResponseStatus";
    }

    @GetMapping("/leave-request-rejected")
    public String submitLeaveRequestRejected(@RequestParam int id, HttpSession session) {

        leaveController.setStatusById(id,"Rejected");

        Manager manager = managerService.getCurrentManager();
        manager.setHandledRequests(leaveController.getLeaveRequestsList());

        List<LeaveRequest> leaveRequestsList = manager.getHandledRequests();

        session.setAttribute("userdata", manager);
        session.setAttribute("leaveRequestsHandler", leaveRequestsList);
        return "/manager/managerLeaveResponseStatus";
    }

    public List<Manager> getManagers() {
        return managerService.getManagers();
    }

    public Manager getManagerById(int managerId) {
        //int id=Integer.parseInt(managerId);
        return managerService.getManagerById(managerId);
    }
}
