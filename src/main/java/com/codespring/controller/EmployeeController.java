package com.codespring.controller;

//import com.codespring.model.EmailDetails;

//import com.codespring.cron.TutorialScheduler;
import com.codespring.dto.EmployeeDTO;
import com.codespring.model.Employee;
import com.codespring.services.EmailService;
import com.codespring.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/org")
@CrossOrigin
public class EmployeeController {

//    @Autowired private EmailService emailService;

    @Autowired
    EmployeeServices employeeServices;



    // add Employee
    @PostMapping("/employee")
    public void addEmployee(){
        employeeServices.insertEmployee();
    }

    @GetMapping("/employee")
    public List<Employee> getAllEmployee(){
        return employeeServices.getAllEmployee();
    }

    @GetMapping("/employee/{id}")
    public EmployeeDTO getByIdEmployee(@PathVariable("id") Long id){
        return employeeServices.getByIdEmployee(id);
    }

//    @PutMapping("/employee")
//    public List<Employee> updateEmployee(@RequestBody List<Employee> emp){
//        return employeeServices.insertEmployee(emp);
//    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeServices.deleteEmployeeById(id);

        return "Delete Employee Success !!";
    }
}
