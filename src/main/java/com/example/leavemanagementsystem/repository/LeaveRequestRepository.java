package com.example.leavemanagementsystem.repository;


import com.example.leavemanagementsystem.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Integer>{
    List<LeaveRequest> findByEmployee_Id(int employeeId);

    List<LeaveRequest> findByManager_Id(int managerId);
}
