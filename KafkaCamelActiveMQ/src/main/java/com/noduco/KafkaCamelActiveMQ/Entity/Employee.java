package com.noduco.KafkaCamelActiveMQ.Entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Employee {

    @Id
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private Date dob;
    private String marital_status;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private EmploymentDetails employment_details;

}
