package com.nouco.SpringCamelProject.entity;

import lombok.*;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.springframework.stereotype.Component;

@Component
@CsvRecord(separator = ",", skipFirstLine = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {

    @DataField( pos = 1, required = true, trim = true )
    private Integer id;

    @DataField( pos = 2, required = true, trim = true )
    private String name;

    @DataField( pos = 3, required = true, trim = true )
    private boolean inStock;

    @DataField( pos = 4, required = true, trim = true )
    private double price;

    @DataField( pos = 5, required = true, trim = true )
    private String Brand;

}
