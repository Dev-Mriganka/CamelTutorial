package com.nouco.SpringCamelProject.route;


import com.nouco.SpringCamelProject.entity.EmployeeCsvRecord;
import com.nouco.SpringCamelProject.service.EmployeeProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;


//@Component
public class CsvReaderRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

////
////        from("{{my.app.source}}")
////                .unmarshal()
////                .bindy(BindyType.Csv, EmployeeCsvRecord.class)
////                .log("Unmarshalled message: ${body}")
////                .split(body())
////                .streaming()
////                .bean("employeeService", "csvRecordToEmployee")
////                .bean("employeeService", "getMap")
////                .wireTap("direct:auditRoute")
//                                                                    // todo: explore aggregate method
////                .aggregate(constant(true), new EmployeeAggregationStrategy())
////                .bean("employeeService", "processEmployeeData")
////                .bean("employeeService", "processEmployeeMap")
//                .end()
//                .process(exchange -> {
//                    EmployeeProcessor processor = exchange.getContext().getRegistry().lookupByNameAndType("employeeService", EmployeeProcessor.class);
//                    exchange.getIn().setBody(processor.getEmployees().toString());
//                    exchange.getIn().setBody(processor.getEmployeeMaps());
//                })
//                .log("Final message: ${body}");
//                .to("file:src/main/resources?fileName=employee2.csv&noop=true");
//                .log("data initiated into database")
//                .to("{{sql.insertEmployee}}")
//                .log("data inserted into database");
//                .to("sql:insert into employee(employeeName, employeeDepartment, hireDate, salary) values (:#${body.employeeName}, :#${body.employeeDepartment}, :#${body.hireDate}, :#${body.salary})?batch=true");


//        from("{{my.app.source}}")
//                .split(body().tokenize("\n", 1, true))
//                //filter if the body is not empty
//                .streaming()
//                .filter().simple("${body} != ''")
//                .process(exchange -> {
//                    String[] data = exchange.getIn().getBody(String.class).split(",");
//                    Employee employee = new Employee( data[0], data[1], LocalDate.parse(data[2]), Double.parseDouble(data[3]));
//                    exchange.getIn().setBody(employee);
//                })
//                .bean("employeeService", "getMap")
////                .aggregate(constant(true), new EmployeeAggregationStrategy())
//                .log("Aggregated message: ${body}")
//                .to("{{sql.insertEmployee}}")
//                .log("data inserted into database")
//                .end();


//        from("file:src/main/resources?fileName=employee.csv&noop=true")
//                .split(body().tokenize("\n", 1, true))
//                .streaming()
//                .stopOnException()
//                .filter().simple("${body} != ''")
//                .log("${body}")
//                .process(exchange -> {
//                    String[] employeeData = exchange.getIn().getBody(String.class).split(",");
//                    Employee employee = new Employee();
//                    employee.setEmployeeID(Integer.parseInt(employeeData[0]));
//                    employee.setEmployeeName(employeeData[1]);
//                    employee.setEmployeeDepartment(employeeData[2]);
//                    employee.setHireDate(LocalDate.parse(employeeData[3]));
//                    employee.setSalary(Double.parseDouble(employeeData[4]));
//                    exchange.getIn().setBody(employee);
//                })
//                .bean("employeeService", "getMap")
//                .log("${body}")
//                .to("sql:insert into employee(employeeID, employeeName, employeeDepartment, hireDate, salary) values (:#employeeID, :#employeeName, :#employeeDepartment, :#hireDate, :#salary)");


//                .to("file:src/main/resources/processed?fileName=${file:name}&move=../processed/${file:name}");


//        from("timer:first-timer?period=100")
//                .setBody(constant("Hello World"))
//                .to("log:first-log");

    }
}
