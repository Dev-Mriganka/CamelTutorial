package com.nouco.SpringCamelProject.route;

import com.nouco.SpringCamelProject.rest.WeatherDataProvider;
import com.nouco.SpringCamelProject.rest.WeatherDto;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.DefaultMessage;

import java.util.Objects;

import static org.apache.camel.Exchange.HTTP_RESPONSE_CODE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

//@Component
public class RestRouteBuilder extends RouteBuilder {

    private final WeatherDataProvider weatherDataProvider;

    public RestRouteBuilder(WeatherDataProvider weatherDataProvider) {
        this.weatherDataProvider = weatherDataProvider;
    }

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto);

        from("rest:get:javadsl/weather/{city}?produces=application/json")
                .outputType(WeatherDto.class)
                .process(this::getWeatherData);

        //        from("rest:get:hello")
        //                .transform().constant("Bye World");


        //        rest("/orders")
        //                .get("{id}")
        //                .to("bean:orderService?method=getOrder(${header.id})")
        //                .post()
        //                .to("bean:orderService?method=createOrder")
        //                .put()
        //                .to("bean:orderService?method=updateOrder")
        //                .delete("{id}")
        //                .to("bean:orderService?method=cancelOrder(${header.id})");
    }


    private void getWeatherData(Exchange exchange) {
        String city = exchange.getMessage().getHeader("city", String.class);
        WeatherDto currentWeather = weatherDataProvider.getCurrentWeather(city);

        if(Objects.nonNull(currentWeather)) {
            Message message = new DefaultMessage(exchange.getContext());
            message.setBody(currentWeather);
            exchange.setMessage(message);
        } else {
            exchange.getMessage().setHeader(HTTP_RESPONSE_CODE, NOT_FOUND.value());
        }
    }

}
