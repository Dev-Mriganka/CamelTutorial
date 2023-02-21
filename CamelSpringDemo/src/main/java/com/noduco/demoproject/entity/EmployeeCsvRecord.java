package com.noduco.demoproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@CsvRecord(separator = ",", skipFirstLine = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeCsvRecord {

    @DataField( pos = 1, required = true, trim = true )
    private String employeeName;

    @DataField( pos = 2, required = true, trim = true )
    private Integer employeeAge;

    @DataField( pos = 3, required = true, trim = true )
    private String employeeGender;

    @DataField( pos = 4, required = true, trim = true )
    private String employeeDepartment;

    @DataField( pos = 5, required = true, trim = true )
    private double employeeSalary;

}
