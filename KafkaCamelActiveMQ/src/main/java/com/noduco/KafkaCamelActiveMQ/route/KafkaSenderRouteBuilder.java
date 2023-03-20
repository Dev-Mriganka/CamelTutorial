package com.noduco.KafkaCamelActiveMQ.route;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;

//@Component
public class KafkaSenderRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:ping?period=10000")
                .setBody(simple("Welcome to Camel ${date:now:yyyy-MM-dd HH:mm:ss}"))
                .to("kafka:myTopic?brokers=localhost:9092");
    }
}

