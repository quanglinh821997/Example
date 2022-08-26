package com.codespring.services;

import com.codespring.dto.EmployeeDTO;
import com.codespring.model.Employee;
import com.codespring.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections4.ListUtils;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class EmployeeServices {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    public void insertEmployee(int size, int type) {
        long startTime = System.currentTimeMillis();
        List<Employee> empList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            empList.add(generateEmployee(i));
        }
        insertAllEmployees(empList, type);
        long endTime = System.currentTimeMillis();
        log.info("Processing time : {}", endTime - startTime);
    }

//    private Employee generateEmployee(int index) {
//        return Employee.builder()
//                .address("No " + index + " Le trong tan")
//                .name("Employee " + index)
//                .checkemail(0)
//                .build();
//    }

    private Employee generateEmployee(int index) {
        return Employee.builder()
                .address("No " + index + " Le Trong Tan")
                .name("Employee " + index)
                .checkemail(0)
                .build();
    }

//    private void insertAllEmployees(List<Employee> employees, int type) {
//        switch (type) {
//            case 0:
//                employeeRepo.saveAll(employees);
//                break;
//            case 1:
//                multiThreadInsert(employees);
//                break;
//            default:
//                batchInsert(employees);
//        }
//    }

    //    private void multiThreadInsert(List<Employee> employees) {
//        List<List<Employee>> output = ListUtils.partition(employees, 2000);
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        List<Future> futures = new ArrayList<>();
//        for (List<Employee> employeeList : output) {
//            futures.add(executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    batchInsert(employeeList);
//                }
//            }));
//        }
//        for (Future future : futures) {
//            try {
//                future.get();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } catch (ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    private void multiThreadInsert(List<Employee> employees) {
        List<List<Employee>> output = ListUtils.partition(employees, 2000);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future> futures = new ArrayList<>();
        for (List<Employee> employeeList : output) {
            futures.add(executor.submit(new Runnable() {
                @Override
                public void run() {
                    log.info("Inserting : " + employeeList.size() + " employees");
                    batchInsert(employeeList);
                }
            }));
        }
        for (Future future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
    }
//    private void batchInsert(List<Employee> employees) {
//        jdbcTemplate.batchUpdate("insert into examplesp.employee (name, address) values(?,?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                Employee item = employees.get(i);
//                ps.setString(1, item.getName());
//                ps.setString(2, item.getAddress());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return employees.size();
//            }
//        });
//    }

    private void batchInsert(List<Employee> employees) {
        jdbcTemplate.batchUpdate("insert into examplesp.employee (name, address) values (?,?)", new BatchPreparedStatementSetter() {
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

    private void insertAllEmployees(List<Employee> employees, int type) {
        switch (type) {
            case 0:
                employeeRepo.saveAll(employees);
            case 1:
                multiThreadInsert(employees);
            default:
                batchInsert(employees);
        }
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

    public void deleteEmployeeById(Long id) {
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
