package com.noduco.KafkaCamelActiveMQ.dto;

import com.noduco.KafkaCamelActiveMQ.Entity.EmploymentDetails;
import lombok.*;


import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeAndEmployment {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private Date dob;
    private String marital_status;
    private EmploymentDetails employment_details;

}
