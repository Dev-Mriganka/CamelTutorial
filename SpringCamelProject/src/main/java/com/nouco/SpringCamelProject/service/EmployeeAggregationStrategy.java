package com.nouco.SpringCamelProject.service;

import com.nouco.SpringCamelProject.entity.Employee;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeAggregationStrategy implements AggregationStrategy {


    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        List<List<String>> newBody = (List<List<String>>) newExchange.getIn().getBody(List.class);
        if (oldExchange == null) {
            List<List<String>> list = new ArrayList<>();
            newBody.forEach(list::add);
            newExchange.getIn().setBody(list);
            return newExchange;
        } else {
            var list = (List<List<String>>) oldExchange.getIn().getBody(List.class);
            newBody.forEach(list::add);
            oldExchange.getIn().setBody(list);
            return oldExchange;
        }

    }

}
