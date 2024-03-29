package com.micropos.gateway;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;
import org.springframework.stereotype.Component;
import org.springframework.integration.webflux.dsl.WebFlux;

@Component
public class HttpInboundGateway {

    @Bean
    public IntegrationFlow inGate() {
        return IntegrationFlows.from(WebFlux.inboundGateway("/delivery")
                        .requestMapping(m -> m.methods(HttpMethod.GET))
                )
                .headerFilter("accept-encoding", false)
                .channel("pos_list_delivery")
                .get();
    }
}
