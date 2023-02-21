package com.noduco.demoproject.route;

import com.noduco.demoproject.service.EmployeeProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCsvSqlRouteBuilder extends RouteBuilder {

    private final CamelContext camelContext;
    public EmployeeCsvSqlRouteBuilder(CamelContext camelContext){
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
//                .process(exchange -> {
//                    List<Map<String,Object>> map = exchange.getIn().getBody(List.class);
//                    var iterator = map.iterator();
//                    exchange.getIn().setBody(iterator);
//                })
                .log("final data: ${body}")
                .log("data ready to insert into database")
                .to("{{sql.insertEmployee}}")
                .log("data inserted into database");

    }





}
