package com.codespring.repository;

import com.codespring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Query(
            value = "Select * From employee Where employee.created_at BETWEEN ADDTIME(CURTIME(),'-0:2:0') and CURTIME();",
            nativeQuery = true)
    List<Employee> findEmployeeByMinutes();


    @Query(
            value = "SELECT * FROM employee where checkemail = 0;",
            nativeQuery = true)
    List<Employee> findEmployeeByCheckEmail();

    @Query(
            value = "UPDATE employee SET employee.checkemail = 1 ;",
            nativeQuery = true)
    void UpdateData();


}
