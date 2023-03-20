package com.noduco.KafkaCamelActiveMQ.service;


import com.noduco.KafkaCamelActiveMQ.Entity.Employee;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.ArrayList;

public class EmployeeAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        Employee newBody = newExchange.getIn().getBody(Employee.class);
        ArrayList<Employee> list;
        if (oldExchange == null) {
            list = new ArrayList<>();
            list.add(newBody);
            newExchange.getIn().setBody(list);
            return newExchange;
        } else {
            list = oldExchange.getIn().getBody(ArrayList.class);
            list.add(newBody);
            return oldExchange;
        }

    }

}
