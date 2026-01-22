package com.example.leavemanagementsystem.repository;


import com.example.leavemanagementsystem.model.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>{
    @Query("SELECT d.no_of_employees FROM Department d WHERE d.department_name = :deptname")
    Integer findNoOfEmployeesByDeptname(@Param("deptname") String deptname);

    @Modifying //“This is not a SELECT query; it changes data (UPDATE/DELETE).”
    @Transactional //Run this update inside a database transaction.
    @Query("UPDATE Department d SET d.no_of_employees = :count WHERE d.department_name = :deptname")
    int updateNoOfEmployeesByDeptname(@Param("deptname") String deptname,
                                      @Param("count") int count);
}
