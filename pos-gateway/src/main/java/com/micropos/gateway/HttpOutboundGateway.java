package com.micropos.gateway;


import com.micropos.dto.DeliveryEntryDto;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;
import org.springframework.stereotype.Component;
import org.springframework.integration.webflux.dsl.WebFlux;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

@Component
public class HttpOutboundGateway {
    @Bean
    public IntegrationFlow outGate() {
        return IntegrationFlows.from("pos_list_delivery")
                .handle(WebFlux.outboundGateway(message ->
                                UriComponentsBuilder.fromUriString("http://localhost:8086/delivery")
                                        .toUriString())
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(ArrayList.class))
                //.<Joke, String>transform((j) -> j.getValue())
                //.handle(System.out::println)
                .get();
    }
}