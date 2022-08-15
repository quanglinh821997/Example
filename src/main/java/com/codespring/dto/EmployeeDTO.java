package com.codespring.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDTO {

    private Long id;
    private String name;
    private String address;
    private Date created_at;
    private int checkemail;
    private String abc;
}
