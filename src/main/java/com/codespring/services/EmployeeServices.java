package com.codespring.services;

import com.codespring.model.Employee;
import com.codespring.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServices {

    @Autowired
    EmployeeRepo employeeRepo;


    public Employee insertEmployee(Employee emp){
        return employeeRepo.save(emp);
    }


    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    public Employee getByIdEmployee(Long id) {
        Employee employee = employeeRepo.findById(id).get();
        employee.setName("new name");
        return employee;
    }

    public void deleteEmployeeById(Long id){
        employeeRepo.deleteById(id);
    }


    public List<Employee> getEmployeeByMinutes() {
        return employeeRepo.findEmployeeByMinutes();
    }

    public void updateData() {
        employeeRepo.updateData();
    }

    public List<Employee> getEmployeeByCheckEmail() {
        return employeeRepo.findEmployeeByCheckEmail();
    }



}
