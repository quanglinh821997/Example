package com.codespring.repository;

import com.codespring.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    @Query(
            value = "Select * From employee Where employee.created_at BETWEEN ADDTIME(CURTIME(),'-0:2:0') and CURTIME();",
            nativeQuery = true)
    List<Employee> findEmployeeByMinutes();


    @Query(
            value = "SELECT * FROM employee where checkemail = 0;",
            nativeQuery = true)
    List<Employee> findEmployeeByCheckEmail();

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE employee SET checkemail = 1 ;",
            nativeQuery = true)
    void updateData();

    Optional<Employee> findById(Long id);


}
