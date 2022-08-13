package com.codespring.cron;

import com.codespring.controller.EmployeeController;
import com.codespring.model.Employee;
import com.codespring.services.EmailService;
import com.codespring.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class TutorialScheduler {

    @Autowired private EmailService emailService;
    @Autowired
    EmployeeServices employeeServices;

    @Scheduled(fixedRate = 120000L)
    public void cheduleTaskUsingCronExpression() {
            List<Employee> employeeList = employeeServices.getAllEmployee();
            if (employeeList == null) {
                System.out.println(new Date().toString() + " Employee List is Empty...");
            } else {
                System.out.println(new Date().toString() + " Employee List is not Empty and size is: " + employeeList.size());
                var empList = employeeServices.getEmployeeByMinutes();

                if (empList != null) {
                    for (Employee emp:empList) {
                        emailService.sendSimpleMail("quanglinh238297@gmail.com","Hello" + emp.getName(), "Demo");
                    }
                }

            }
    }
}

