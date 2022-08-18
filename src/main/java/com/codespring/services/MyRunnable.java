package com.codespring.services;

import com.codespring.model.Employee;

import java.util.List;

public class MyRunnable implements Runnable {

    private int index;

    private List<Employee> employees;

    public MyRunnable(int index, List<Employee> employees) {
        this.index = index;
        this.employees = employees;
    }

    @Override
    public void run() {

        employees.add(Employee.builder()
                .address("No " + index + " Le trong tan")
                .name("Employee " + index)
                .checkemail(0)
                .build());
    }
}
