package com.noduco.demoproject.route;

import com.noduco.demoproject.entity.Employee;
import com.noduco.demoproject.service.EmployeeProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Component
public class EmployeeCsvJdbcRouteBuilder extends RouteBuilder {

    private final CamelContext camelContext;

    @Autowired
    DataSource dataSource;

    public EmployeeCsvJdbcRouteBuilder(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure() throws Exception {

//        from("{{my.app.source}}")
////                .unmarshal()
////                .bindy(BindyType.Csv, EmployeeCsvRecord.class)
//                .split(body().tokenize("\n", 100, true))
//                .unmarshal()
//                .csv()
//                .process(new EmployeeProcessor(camelContext));
//
//        from("direct:processCsvRecordDb")
//                .routeId("processCsvRecordDb")
////                .process(exchange -> {
////                    List<Map<String,Object>> map = exchange.getIn().getBody(List.class);
////                    var iterator = map.iterator();
////                    exchange.getIn().setBody(iterator);
////                })
//                .log("final data: ${body}")
//                .log("data ready to insert into database")
//                .to("{{sql.insertEmployee-single}}")
//                .log("data inserted into database");


//        from("timer://foo?period=60000").setBody(constant("select * from Employees")).to("jdbc:dataSource")
//            .process(new Processor() {
//                public void process(Exchange exchange) throws Exception {
//                    //the camel jdbc select query has been executed. We get the list of employees.
//                    ArrayList<Map<String, Object>> dataList = (ArrayList<Map<String, Object>>) exchange.getIn().getBody();
//                    List<Employee> employees = new ArrayList<Employee>();
//                    for (Map<String, Object> data : dataList) {
//                        employees.add( Employee.builder()
//                                .employeeID((Integer) data.get("EmployeeID"))
//                                .employeeName((String) data.get("EmployeeName"))
//                                .employeeAge((Integer) data.get("EmployeeAge"))
//                                .employeeGender((String) data.get("EmployeeGender"))
//                                .employeeDepartment((String) data.get("EmployeeDepartment"))
//                                .employeeSalary((Double) data.get("EmployeeSalary"))
//                                .build());
//                    }
//                    exchange.getIn().setBody(employees);
//                }
//            })
//            .to("log:select?showAll=true&multiline=true");

    }

}
