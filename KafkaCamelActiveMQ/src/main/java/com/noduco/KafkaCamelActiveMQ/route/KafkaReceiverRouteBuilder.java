package com.noduco.KafkaCamelActiveMQ.route;


import com.noduco.KafkaCamelActiveMQ.Entity.Employee;
import com.noduco.KafkaCamelActiveMQ.Entity.KafkaMessage;
import com.noduco.KafkaCamelActiveMQ.dto.EmployeeAndAddress;
import com.noduco.KafkaCamelActiveMQ.dto.EmployeeAndEmployment;
import com.noduco.KafkaCamelActiveMQ.dto.EmployeeBasicDetails;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiverRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("kafka:myTopic?brokers=localhost:9092")
                .unmarshal().json(JsonLibrary.Jackson, KafkaMessage.class)
                .to("direct:myTopic");

        from("direct:myTopic")
                .process(exchange -> {
                    KafkaMessage kafkaMessage = exchange.getIn().getBody(KafkaMessage.class);
                    //set the employeeId to the header
                    exchange.getIn().setHeader("employeeId", kafkaMessage.getEmployeeId());
                    exchange.setProperty("employeeId", kafkaMessage.getEmployeeId());
                    exchange.setProperty("address", kafkaMessage.isAddress());
                    exchange.setProperty("employment", kafkaMessage.isEmployment());
                })
                .to("direct:getEmployee");

        from("direct:getEmployee")
                .toD("jpa://" + Employee.class.getName() + "?query=select b from " + Employee.class.getName()+ " b where b.id= ${exchangeProperty.employeeId}")
                .to("direct:sendToActiveMQ");

        from("direct:sendToActiveMQ")
                .process(exchange -> {
                    Employee employee = exchange.getIn().getBody(Employee.class);
                    System.out.println("Employee Id: " + employee.getId());
                    boolean address = exchange.getProperty("address", boolean.class);
                    boolean employment = exchange.getProperty("employment", boolean.class);
                    if(address && !employment){
                        System.out.println("block 1");
                        EmployeeAndAddress employeeAndAddress = employeeAndAddressConverter(employee);
//                        System.out.println(employeeAndAddress);
                        exchange.getIn().setBody(employeeAndAddress);
                    } else if ( !address && employment){
                        System.out.println("block 2");
                        EmployeeAndEmployment employeeAndEmployment = employeeAndEmploymentConverter(employee);
                        exchange.getIn().setBody(employeeAndEmployment);
                    } else if (!address && !employment){
                        System.out.println("block 3");
                        EmployeeBasicDetails employeeBasicDetails = employeeBasicDetailsConverter(employee);
                        exchange.getIn().setBody(employeeBasicDetails);
                    }

                })
                .to("log:com.noduco.KafkaCamelActiveMQ?level=ERROR&showAll=true&multiline=true");

    }

    public EmployeeAndAddress employeeAndAddressConverter(Employee employee){
        EmployeeAndAddress employeeAndAddress = new EmployeeAndAddress();
        employeeAndAddress.setId(employee.getId());
        employeeAndAddress.setName(employee.getName());
        employeeAndAddress.setEmail(employee.getEmail());
        employeeAndAddress.setPhone(employee.getPhone());
        employeeAndAddress.setGender(employee.getGender());
        employeeAndAddress.setDob(employee.getDob());
        employeeAndAddress.setMarital_status(employee.getMarital_status());
        employeeAndAddress.setAddress(employee.getAddress());
        return employeeAndAddress;
    }

    public EmployeeAndEmployment employeeAndEmploymentConverter(Employee employee){
        EmployeeAndEmployment employeeAndEmployment = new EmployeeAndEmployment();
        employeeAndEmployment.setId(employee.getId());
        employeeAndEmployment.setName(employee.getName());
        employeeAndEmployment.setEmail(employee.getEmail());
        employeeAndEmployment.setPhone(employee.getPhone());
        employeeAndEmployment.setGender(employee.getGender());
        employeeAndEmployment.setDob(employee.getDob());
        employeeAndEmployment.setMarital_status(employee.getMarital_status());
        employeeAndEmployment.setEmployment_details(employee.getEmployment_details());
        return employeeAndEmployment;
    }

    public EmployeeBasicDetails employeeBasicDetailsConverter(Employee employee){
        EmployeeBasicDetails employeeBasicDetails = new EmployeeBasicDetails();
        employeeBasicDetails.setId(employee.getId());
        employeeBasicDetails.setName(employee.getName());
        employeeBasicDetails.setEmail(employee.getEmail());
        employeeBasicDetails.setPhone(employee.getPhone());
        employeeBasicDetails.setGender(employee.getGender());
        employeeBasicDetails.setDob(employee.getDob());
        employeeBasicDetails.setMarital_status(employee.getMarital_status());
        return employeeBasicDetails;
    }

}
