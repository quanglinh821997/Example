package com.codespring.services;

import com.codespring.dto.EmployeeDTO;
import com.codespring.model.Employee;
import com.codespring.repository.EmployeeRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public EmployeeDTO getByIdEmployee(Long id) {
        Employee employee = employeeRepo.findById(id).get();
        employee.setName("new name");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
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
