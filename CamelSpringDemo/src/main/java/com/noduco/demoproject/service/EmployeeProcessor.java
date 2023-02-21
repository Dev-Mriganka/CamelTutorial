package com.noduco.demoproject.service;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeProcessor implements Processor {

    private final ProducerTemplate producerTemplate;

    public EmployeeProcessor(CamelContext camelContext) {
        this.producerTemplate = camelContext.createProducerTemplate(1);
    }


//    public Map<String, Object> getMap(List<String> employee) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("employeeName", employee.get(0));
//        map.put("employeeAge", employee.get(1));
//        map.put("employeeGender", employee.get(2));
//        map.put("employeeDepartment", employee.get(3));
//        map.put("employeeSalary", employee.get(4));
//        return map;
//    }


    public Map<String, Object> getMap(List<String> employee) {
        Map<String, Object> map = new HashMap<>();
        map.put("employeeName", employee.get(0));
        map.put("employeeAge", employee.get(1));
        map.put("employeeGender", employee.get(2));
        map.put("employeeDepartment", employee.get(3));
        map.put("employeeSalary", employee.get(4));
        return map;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        List<List<String>> list = exchange.getIn().getBody(List.class);

        List<Map<String,Object>> mapList = new ArrayList<>();

        list.forEach(empList ->{
            var empMap = getMap(empList);
            mapList.add(empMap);
        });

        exchange.getIn().setBody(mapList);
        producerTemplate.send("direct:processCsvRecordDb", exchange);

    }

}
