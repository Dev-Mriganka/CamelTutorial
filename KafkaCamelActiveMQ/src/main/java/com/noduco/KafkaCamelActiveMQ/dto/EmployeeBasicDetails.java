package com.noduco.KafkaCamelActiveMQ.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeBasicDetails {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private Date dob;
    private String marital_status;

}
