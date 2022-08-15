package com.codespring.cron;

import com.codespring.model.Employee;
import com.codespring.services.EmailService;
import com.codespring.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TutorialScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    EmployeeServices employeeServices;

    @Scheduled(fixedRate = 120000L)
    public void scheduleTaskUsingCronExpression() {
//        var employees = employeeServices.getEmployeeByMinutes();
//        if (employees.isEmpty()) {
//            System.out.println(new Date() + " Employee List is Empty...");
//        } else {
//            for (Employee employee : employees) {
//                emailService.sendSimpleMail("quanglinh238297@gmail.com", "Hello " + employee.getName(), "Demo");
//            }
//        }

        var employeeList = employeeServices.getEmployeeByCheckEmail();
        if (employeeList.isEmpty()) {
            System.out.println(new Date() + " Employee List is Empty...");
        } else {
            for (Employee employee : employeeList) {
                emailService.sendSimpleMail("quanglinh238297@gmail.com", "Hello " + employee.getName(), "Demo");
            }
        }
        employeeServices.updateData();
    }
}

