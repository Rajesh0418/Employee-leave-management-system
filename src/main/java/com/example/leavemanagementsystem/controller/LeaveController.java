package com.example.leavemanagementsystem.controller;

import com.example.leavemanagementsystem.model.LeaveRequest;
import com.example.leavemanagementsystem.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/leave")
@Controller
public class LeaveController {

    @Autowired
    private LeaveService leaveService;


    @GetMapping("/add-leave-request")
    public void addLeaveRequest(LeaveRequest leaveRequest) {
        leaveService.addLeaveRequest(leaveRequest);
    }

    public List<LeaveRequest> getLeaveRequestsByEmployeeId(int id) {
        return leaveService.getLeaveRequestsByEmployeeId(id);
    }

    public List<LeaveRequest> getLeaveRequestsList() {
        return leaveService.getLeaveRequestsList();
    }

    public List<LeaveRequest> getLeaveRequestsByManagerId(int id) {
        return leaveService.getLeaveRequestsByManagerId(id);
    }

    public void setStatusById(int id,String status) {
        leaveService.setStatusById(id,status);
    }

}
