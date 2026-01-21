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

    //employee
    public void addLeaveRequest(LeaveRequest leaveRequest) {
        requestsList.add(leaveRequest);
        leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getLeaveRequestsByEmployeeId(int id) {
        requestsList = leaveRequestRepository.findByEmployee_Id(id);
        return requestsList;
    }

    public List<LeaveRequest> getLeaveRequestsList() {
        return requestsList;
    }


    //manager
    public void setStatusById(int id,String status) {
        for(LeaveRequest leaveRequest1:requestsList) {
            if (leaveRequest1.getId() == id) {
                leaveRequest1.setStatus(status);
                leaveRequestRepository.save(leaveRequest1);
                break;
            }
        }
    }

    public List<LeaveRequest> getLeaveRequestsByManagerId(int id) {
        requestsList = leaveRequestRepository.findByManager_Id(id);
        return requestsList;
    }
}
