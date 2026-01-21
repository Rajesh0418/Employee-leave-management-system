package com.example.leavemanagementsystem.service;

import com.example.leavemanagementsystem.model.LeaveRequest;
import com.example.leavemanagementsystem.repository.LeaveRequestRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Getter
    List<LeaveRequest> requestsList;

    @Autowired
    LeaveRequestRepository leaveRequestRepository;

    // saving employee leave request
    public void addLeaveRequest(LeaveRequest leaveRequest) {
        requestsList.add(leaveRequest);
        leaveRequestRepository.save(leaveRequest);
    }

    // getting leave request by emp ID for displaying at emp dashboard
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(int id) {
        requestsList = leaveRequestRepository.findByEmployee_Id(id);
        return requestsList;
    }

    //getting all leave requests
    public List<LeaveRequest> getLeaveRequestsList() {
        return requestsList;
    }


    //manager
    // leave approval or rejected by manager id
    public void setStatusById(int manager_id,String status) {
        for(LeaveRequest leaveRequest1:requestsList) {
            if (leaveRequest1.getId() == manager_id) {
                leaveRequest1.setStatus(status);
                leaveRequestRepository.save(leaveRequest1);
                break;
            }
        }
    }

    // getting leave request by emp ID for displaying at manager dashboard
    public List<LeaveRequest> getLeaveRequestsByManagerId(int id) {
        requestsList = leaveRequestRepository.findByManager_Id(id);
        return requestsList;
    }
}
