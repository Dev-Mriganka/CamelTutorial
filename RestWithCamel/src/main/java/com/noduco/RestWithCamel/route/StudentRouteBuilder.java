package com.noduco.RestWithCamel.route;

import com.noduco.RestWithCamel.entity.Student;
import com.noduco.RestWithCamel.service.StudentService;
import com.noduco.RestWithCamel.service.WeatherDataProvider;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


import java.util.Map;

@Component
@RequiredArgsConstructor
public class StudentRouteBuilder extends RouteBuilder {

    private final WeatherDataProvider weatherDataProvider;

    private StudentService studentService;


    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .bindingMode("json");

//        rest()
//                .consumes("application/json").produces("application/json")
//                .get("/student/{id}").outType(Student.class).to("bean:studentService?method=getStudent(${header.id})")
//                .get("/student").outType(Student.class).to("bean:studentService?method=getAllStudents")
//                .post("/student").type(Student.class).outType(Student.class).to("bean:studentService?method=registerStudent")
//                .put("/student").type(Student.class).outType(Student.class).to("bean:studentService?method=updateStudent")
//                .delete("/student/{id}").outType(Student.class).to("bean:studentService?method=deleteStudent(${header.id})");

        rest()
                .consumes("application/json").produces("application/json")
                .get("/student/{id}").outType(Student.class)
                .route()
                .toD("jpa://" + Student.class.getName() + "?resultClass="+ Student.class.getName() + "&query=select s from Student s where s.id= ${header.id}")
//                .process(exchange ->{
//                    Student student = exchange.getIn().getBody(Student.class);
//                    if(Objects.isNull(student)){
//                        exchange.getIn().setBody("id doesn't exist");
//                    }
//                })
        ;

        rest()
                .consumes("application/json").produces("application/json")
                .get("/student/search/{name}").outType(Student.class)
                .route()
                .process(
                        exchange -> {
                            Map<String, Object> params = Map.of("name", "%"+(exchange.getIn().getHeader("name"))+"%");
                            System.out.println("params: " + params);
                            exchange.getContext().getRegistry().bind("myMap", params);
                        }
                )
                .toD("jpa://" + Student.class.getName() + "?resultClass="+ Student.class.getName() + "&namedQuery=findByName&parameters=#myMap");

        rest()
                .consumes("application/json").produces("application/json")
                .get("/student").outType(Student.class).to("jpa://"+ Student.class.getName() + "?resultClass="+ Student.class.getName() + "&query=select s from Student s")
                .post("/student").type(Student.class).outType(Student.class).to("jpa://"+ Student.class.getName() + "?usePersist=true")
                .put("/student/{id}").type(Student.class).outType(Student.class).route()
//                .choice().when().simple("${header.id} < 1").bean(StudentRouter.class, "negativeId").otherwise()
                .process(new Processor() {
                    @Override
                    public void process(final Exchange exchange) {
                        Student student = exchange.getIn().getBody(Student.class);
                        Integer id = Integer.valueOf(exchange.getIn().getHeader("id").toString());
                        student.setId(id);
                        exchange.getIn().setBody(student, Student.class);
                    }
                })
                .to("jpa:" + Student.class.getName() + "?useExecuteUpdate=true")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204));

        rest()
                .consumes("application/json").produces("application/json")
                .delete("/student/{id}").outType(String.class)
                .toD("jpa://"+ Student.class.getName() +
                         "?nativeQuery=DELETE FROM Student s WHERE s.id = ${header.id}&useExecuteUpdate=true")
                .route()
                .setBody(simple("Student with id ${header.id} deleted"));


//        from("jpa:Student?consumeDelete=false&consumer.namedQuery=findAll")
//                .marshal().json()
//                .log("Retrieved students: ${body}");
//                .to("restlet:http://localhost:8080/students?restletMethod=GET");

//                .post().consumes("application/json").type(Student.class).outType(Student.class)
//                .to("jpa:Student")
//                .put().consumes("application/json").type(Student.class).outType(Student.class)
//                .to("jpa:Student")
//                .delete("/{id}").outType(Student.class)
//                .to("jpa:Student?id=#${header.id}&operation=removeById");
//
//        from("jpa:Student?consumeDelete=false&consumer.namedQuery=findAll")
//                .marshal().json()
//                .log("Retrieved students: ${body}")
//                .to("restlet:http://localhost:8080/students?restletMethod=GET");
//
//        from("restlet:http://localhost:8080/students?restletMethod=POST")
//                .unmarshal().json(Student.class)
//                .to("jpa:Student");
//
//        from("restlet:http://localhost:8080/students/{id}?restletMethod=PUT")
//                .unmarshal().json(Student.class)
//                .to("jpa:Student");
//
//        from("restlet:http://localhost:8080/students/{id}?restletMethod=DELETE")
//                .to("jpa:Student?id=#${header.id}&operation=removeById");



//      from("rest:get:/weather/{city}?produces=application/json&consumes=application/json")
//            .outputType(WeatherDto.class)
//            .process(this::getWeatherData);
    }

//    public void getWeatherData(Exchange exchange) {
//        String city = exchange.getIn().getHeader("city", String.class);
//        WeatherDto dto = weatherDataProvider.getCurrentWeather(city);
//
//        Message message = new DefaultMessage(exchange.getContext());
//        message.setBody(dto);
//        exchange.setMessage(message);
//    }

}
