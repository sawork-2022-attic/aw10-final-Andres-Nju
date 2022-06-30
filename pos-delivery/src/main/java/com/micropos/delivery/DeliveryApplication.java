package com.micropos.delivery;

import com.micropos.delivery.repository.DeliveryRepository;
import com.micropos.delivery.repository.DeliveryRepositoryImpl;
import com.micropos.delivery.service.DeliveryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeliveryApplication {

    public static void main(String[] args) {
        //SpringApplication.run(DeliveryApplication.class, args);
        SpringApplication application = new SpringApplication(DeliveryApplication.class);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run(args);
    }

    @Bean
    DeliveryRepository deliveryRepository() {
        return new DeliveryRepositoryImpl();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
