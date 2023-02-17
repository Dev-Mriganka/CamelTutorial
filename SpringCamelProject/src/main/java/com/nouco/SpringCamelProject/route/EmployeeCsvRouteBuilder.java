package com.nouco.SpringCamelProject.route;

import com.nouco.SpringCamelProject.service.BatchSizePredicate;
import com.nouco.SpringCamelProject.service.EmployeeAggregationStrategy;
import com.nouco.SpringCamelProject.service.EmployeeProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@Component
public class EmployeeCsvRouteBuilder extends RouteBuilder {

    private final CamelContext camelContext;
    public EmployeeCsvRouteBuilder(CamelContext camelContext){
        this.camelContext = camelContext;
    }

    @Override
    public void configure() throws Exception {

        from("{{my.app.source}}")
//                .unmarshal()
//                .bindy(BindyType.Csv, EmployeeCsvRecord.class)
                .split(body().tokenize("\n", 100, true))
                .unmarshal()
                .csv()
                .process(new EmployeeProcessor(camelContext));

        from("direct:processCsvRecordDb")
                .routeId("processCsvRecordDb")
                .process(exchange -> {
                    List<Map<String,Object>> map = exchange.getIn().getBody(List.class);
                    var iterator = map.iterator();
                    exchange.getIn().setBody(iterator);
                })
                .log("data ready to insert into database")
                .to("{{sql.insertEmployee}}")
                .log("data inserted into database");

    }

}
