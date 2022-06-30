package com.micropos.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropos.dto.CartDto;
import com.micropos.dto.CartItemDto;
import com.micropos.dto.DeliveryEntryDto;
import com.micropos.dto.OrderDto;
import com.micropos.order.mapper.OrderMapper;
import com.micropos.order.model.Item;
import com.micropos.order.model.Order;
import com.micropos.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderRepository orderRepository;

    private OrderMapper orderMapper;

    private StreamBridge streamBridge;

    private final String DELIVERY_URL = "http://POS-DELIVERY/delivery";

    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) { this.orderMapper = orderMapper; }

    @Autowired
    public void setStreamBridge(StreamBridge streamBridge) { this.streamBridge = streamBridge; }

    @Override
    public Mono<Order> createOrder(CartDto cartDto, String time) {
        Order order = new Order().time(time);
        List<Item> items = new ArrayList<>();
        for (CartItemDto cartItem : cartDto.getItems()) {
            items.add(
                    new Item().id(null)
                            .productId(cartItem.getProduct().getId())
                            .productName(cartItem.getProduct().getName())
                            .unitPrice(cartItem.getProduct().getPrice())
                            .quantity(cartItem.getAmount()));
        }
        order.items(items);
        order = orderRepository.saveOrder(order);
        //orderDtoToSend = orderMapper.toOrderDto(order);
        //sendOrder(order); // send order to rabbitmq

        OrderDto orderDto = orderMapper.toOrderDto(order);
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = null;
        try {
            request = new HttpEntity<>(mapper.writeValueAsString(orderDto), headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        restTemplate.postForObject(DELIVERY_URL, request, DeliveryEntryDto.class);
        return Mono.just(order);
    }

//    private void sendOrder(Order order) {
//        log.info("send {}", orderMapper.toOrderDto(order).toString());
//        streamBridge.send("order-send", orderMapper.toOrderDto(order));
//    }
//
//    private OrderDto orderDtoToSend = new OrderDto().id(-1).time("2022-1-1");
//
//    @Bean
//    public Supplier<OrderDto> supplyOrder() {
//        return () -> {
//            log.info("supply {}", orderDtoToSend.toString());
//            return orderDtoToSend;
//        };
//    }

    @Override
    public Flux<Order> getAllOrders() {
        return orderRepository.findAllOrders();
    }

    @Override
    public Mono<Order> getOrder(Integer orderId) {
        return orderRepository.findOrderById(orderId);
    }
}
