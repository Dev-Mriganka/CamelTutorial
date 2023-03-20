package com.noduco.KafkaCamelActiveMQ.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noduco.KafkaCamelActiveMQ.Entity.Employee;
import com.noduco.KafkaCamelActiveMQ.service.EmployeeAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Component
public class EmployeeRestRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .bindingMode("json");

        rest()
                .consumes("application/json").produces("application/json")
                // batch insert with camel rest and jpa component
                .post("/employee/batch").type(ArrayList.class).outType(ArrayList.class)
                .to("direct:insertBatch");

        from("direct:insertBatch")
                .routeId("insertBatchRoute")
                .split(body())
                .streaming()
                .process(exchange -> {
                    LinkedHashMap linkedHashMap = exchange.getIn().getBody(LinkedHashMap.class);
                    ObjectMapper mapper = new ObjectMapper();
                    Employee employee = mapper.convertValue(linkedHashMap, Employee.class);
                    exchange.getIn().setBody(employee);
                })
                .aggregate(constant(true), new EmployeeAggregationStrategy())
                .completionSize(50)
                .to("jpa:" + Employee.class.getName() + "?usePersist=true&entityType=java.util.List");

        rest()
                .consumes("application/json").produces("application/json")
                .post("/employee").type(Employee.class).outType(Employee.class)
                .to("jpa:" + Employee.class.getName() + "?usePersist=true");

    }

}
