package com.codespring.services;

import com.codespring.dto.EmployeeDTO;
import com.codespring.model.Employee;
import com.codespring.repository.EmployeeRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServices {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    public void insertEmployee(){
        List<Employee> empList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            empList.add(generateEmployee(i));
        }
        long startTime = System.currentTimeMillis();
        insertAllEmployees(empList);
        long endTime = System.currentTimeMillis();
        System.out.println("Processing time: " + (endTime - startTime));
    }

    private Employee generateEmployee(int index) {
        return Employee.builder()
                .address("No " + index + " Le trong tan")
                .name("Employee " + index)
                .checkemail(0)
                .build();
    }

    private void insertAllEmployees(List<Employee> employees) {
//        employeeRepo.saveAll(employees);
        batchInsert(employees);
    }
    private void batchInsert(List<Employee> employees) {
        jdbcTemplate.batchUpdate("insert into examplesp.employee (name, address) values(?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Employee item = employees.get(i);
                ps.setString(1, item.getName());
                ps.setString(2, item.getAddress());
            }

            @Override
            public int getBatchSize() {
                return employees.size();
            }
        });
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
