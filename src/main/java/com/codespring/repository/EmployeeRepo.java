package com.codespring.repository;

import com.codespring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Query(
            value = "Select * From employee Where employee.created_at BETWEEN ADDTIME(CURTIME(),'-0:2:0') and CURTIME();",
            nativeQuery = true)
            List<Employee> findEmployeeByMinutes();
}
