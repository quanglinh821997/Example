package com.codespring.controller;

import com.codespring.model.Employee;
import com.codespring.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org")
@CrossOrigin
public class EmployeeController {

    @Autowired
    EmployeeServices employeeServices;

    // add Employee
    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee emp){
        return employeeServices.insertEmployee(emp);
    }

    @GetMapping("/employee")
    public List<Employee> getAllEmployee(){
        return employeeServices.getAllEmployee();
    }

    @GetMapping("/employee/{id}")
    public Employee getByIdEmployee(@PathVariable("id") Long id){
        return employeeServices.getByIdEmployee(id);
    }

    @PutMapping("/employee")
    public Employee updateEmployee(@RequestBody Employee emp){
        return employeeServices.insertEmployee(emp);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeServices.deleteEmployeeById(id);

        return "Delete Employee Success !!";
    }

}
