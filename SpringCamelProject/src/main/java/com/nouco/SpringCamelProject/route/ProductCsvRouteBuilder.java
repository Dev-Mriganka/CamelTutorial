package com.nouco.SpringCamelProject.route;

import com.nouco.SpringCamelProject.entity.Product;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;

import static org.apache.camel.Exchange.CORRELATION_ID;

//@Component
public class ProductCsvRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("{{my.product.source}}")
                .unmarshal()
                .bindy(BindyType.Csv, Product.class)
                .split(body())
                .streaming()
                .process(exchange -> {
                    Product product = exchange.getIn().getBody(Product.class);
                    Message message = exchange.getMessage();
                    message.setHeader(CORRELATION_ID, product.getBrand());
                })
//                .aggregate(header(CORRELATION_ID),new ProductAggregationStrategy())
//                //todo: collect all and complete on csv file end
//                .completionOnNewCorrelationGroup()
//                .log(LoggingLevel.ERROR, "${header." + CORRELATION_ID + "} ${body}");
                .multicast()
                .to("direct:divi", "direct:wordpress");

        from("direct:divi")
                .filter(header(CORRELATION_ID).isEqualTo("Divi"))
                .process(exchange -> exchange.getIn().setBody(exchange.getIn().getBody(Product.class).toString()))
                .log(LoggingLevel.ERROR, "${header." + CORRELATION_ID + "} ${body}")
                .to("file:src/main/resources/output?fileName=divi.txt&fileExist=Append");

        from("direct:wordpress")
                .filter(header(CORRELATION_ID).isEqualTo("WordPress"))
                .process(exchange -> exchange.getIn().setBody(exchange.getIn().getBody(Product.class).toString()))
                .log(LoggingLevel.ERROR, "${header." + CORRELATION_ID + "} ${body}")
                        //todo: how to write to new line
                .to("file:src/main/resources/output?fileName=wordpress.txt&fileExist=Append");

    }

}
