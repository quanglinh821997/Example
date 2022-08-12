package com.codespring.repository;

import com.codespring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
