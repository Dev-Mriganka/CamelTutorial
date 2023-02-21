package com.noduco.demoproject.route;

import com.noduco.demoproject.entity.Employee;
import com.noduco.demoproject.entity.EmployeeCsvRecord;

import com.noduco.demoproject.service.EmployeeAggregationStrategy;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.springframework.stereotype.Component;

import java.awt.print.Book;


//@Component
public class EmployeeCsvJpaRouteBuilder extends RouteBuilder {

    private final CamelContext camelContext;
    public EmployeeCsvJpaRouteBuilder(CamelContext camelContext){
        this.camelContext = camelContext;
    }

    @Override
    public void configure() throws Exception {

//        from("{{my.app.source}}")
//                .unmarshal()
//                .bindy(BindyType.Csv, EmployeeCsvRecord.class)
//                .split(body())
//                .streaming()
//                .bean("employeeService", "convertAndTransform")
//                .bean("employeeService", "processEmployeeData")
//                .aggregate(constant(true), new EmployeeAggregationStrategy())
//                .completionSize(100)
//                .to("jpa:" + Employee.class.getName() + "?usePersist=true&entityType=java.util.List")
//                .log("Final message: ${body}");


        from("timer://foo?period=60000")
                .setHeader("id", constant(1))
//                .log("selecting all books")
//            .to("jpa://"+ Employee.class.getName() + "?resultClass="+ Employee.class.getName() + "&namedQuery=findAll&maximumResults=5")
                .toD("jpa://" + Employee.class.getName() + "?query=select b from " + Employee.class.getName()+ " b where b.id= ${header.id}", 5)
                .to("log:select?showAll=true&multiline=true");
//            .log("Final message: ${body}");
//                .log("---select all books---");


    }
}

