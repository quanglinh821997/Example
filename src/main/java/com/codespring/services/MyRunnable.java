//package com.codespring.services;
//
//import com.codespring.model.Employee;
//import com.codespring.repository.EmployeeRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.concurrent.ThreadPoolExecutor;
//
//public class MyRunnable implements Runnable {
//
//    private EmployeeRepo employeeRepo;
//    private int  index;
//
//    @Override
//    public void run() {
//
//        employeeRepo.generateEmployee(index);
//    }
//}
