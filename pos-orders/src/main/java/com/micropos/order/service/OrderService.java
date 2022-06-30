package com.micropos.order.service;

import com.micropos.dto.CartDto;
import com.micropos.order.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Mono<Order> createOrder(CartDto cartDto, String time);

    Flux<Order> getAllOrders();

    Mono<Order> getOrder(Integer orderId);
}
