package com.codespring.repository;

import com.codespring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Query(
            value = "SELECT * FROM employee Where 'employee.created_at' BETWEEN ADDTIME(CURTIME(),'-0:2:0') and CURTIME()",
            nativeQuery = true)
            Employee findEmployeeByMinutes();
}
