package com.nouco.SpringCamelProject.route;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.camel.LoggingLevel.ERROR;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class SedaRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:ping?period=200")
                .process(exchange -> {
                    Message message = new DefaultMessage(exchange);
                    message.setBody("ping"+new Date());
                    exchange.setMessage(message);
                })
                .to("direct:complexProcess");

        from("direct:complexProcess")
                .log(ERROR,  "${body}")
                .process(exchange -> SECONDS.sleep(2))
                .end();

    }
}
