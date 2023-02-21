package com.noduco.demoproject.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employee")
@NamedQuery(name = "findAll", query = "SELECT e FROM Employee e")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeID;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_age")
    private Integer employeeAge;

    @Column(name = "employee_gender")
    private String employeeGender;

    @Column(name = "employee_department")
    private String employeeDepartment;

    @Column(name = "employee_salary")
    private Double employeeSalary;

    public Employee(int employeeID, String employeeName, String employeeGender, String employeeDepartment, double employeeSalary) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeGender = employeeGender;
        this.employeeDepartment = employeeDepartment;
        this.employeeSalary = employeeSalary;
    }


}
