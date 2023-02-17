package com.nouco.SpringCamelProject.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Employee {

    private int employeeID;
    private String employeeName;
    private Integer employeeAge;
    private String employeeGender;
    private String employeeDepartment;
    private double employeeSalary;

    public Employee(int employeeID, String employeeName, String employeeGender, String employeeDepartment, double employeeSalary) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeGender = employeeGender;
        this.employeeDepartment = employeeDepartment;
        this.employeeSalary = employeeSalary;
    }
}
