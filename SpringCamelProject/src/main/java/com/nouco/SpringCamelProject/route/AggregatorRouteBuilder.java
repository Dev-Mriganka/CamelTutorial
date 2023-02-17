package com.nouco.SpringCamelProject.route;

import com.nouco.SpringCamelProject.entity.EmployeeCsvRecord;
import com.nouco.SpringCamelProject.service.EmployeeAggregationStrategy;
import com.nouco.SpringCamelProject.service.TimerAggregationStrategy;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

//@Component
public class AggregatorRouteBuilder extends RouteBuilder {
    final String CORRELATION_ID = "correlationId";

    @Override
    public void configure() throws Exception {

        Random random = new Random();

//        from("timer:insurance?period=200")
//                .process(exchange ->
//                {
//                    Message message = exchange.getMessage();
//                    message.setHeader(CORRELATION_ID, random.nextInt(4));
//                    message.setBody(new Date() + "");
//                })
//                .aggregate(header(CORRELATION_ID), new TimerAggregationStrategy())
//                .completionSize(5)
//                .log(LoggingLevel.ERROR, "${header." + CORRELATION_ID + "} ${body}");

        //csv to employee list with aggregation


        from("{{my.app.source}}")
                .unmarshal()
                .bindy(BindyType.Csv, EmployeeCsvRecord.class)
//                .log("Unmarshalled message: ${body}")
                .split(body())
                .streaming()
                .bean("employeeService", "csvRecordToEmployee")
                .bean("employeeService", "getMap")
                .aggregate(constant(true),new EmployeeAggregationStrategy())
                .completionSize(3)
                .log(LoggingLevel.ERROR, "${header." + CORRELATION_ID + "} ${body}");
    }
}
