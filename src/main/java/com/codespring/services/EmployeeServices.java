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
        return employeeRepo.findById(id).get();
    }

    public void deleteEmployeeById(Long id){
        employeeRepo.deleteById(id);
    }


    public List<Employee> getEmployeeByMinutes() {
        return employeeRepo.findEmployeeByMinutes();
    }

    public void UpdateData() {
        employeeRepo.updateData();
    }

    public List<Employee> getEmployeeByCheckEmail() {
        return employeeRepo.findEmployeeByCheckEmail();
    }



}
