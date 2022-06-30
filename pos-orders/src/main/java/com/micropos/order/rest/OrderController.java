package com.micropos.order.rest;

import com.micropos.api.OrdersApi;
import com.micropos.dto.CartDto;
import com.micropos.dto.OrderDto;
import com.micropos.order.mapper.OrderMapper;
import com.micropos.order.model.Order;
import com.micropos.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/orders")
@EnableDiscoveryClient
public class OrderController implements OrdersApi {

    private OrderMapper orderMapper;

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Override
    @PostMapping()
    public Mono<ResponseEntity<OrderDto>> createOrder(@RequestBody  Mono<CartDto> cartDtoMono, final ServerWebExchange exchange) {
//        //System.out.println("order controller: createOrder: " + cartDto.toString());
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = df.format(new Date());
//
//        Order order = orderService.createOrder(cartDto, date);
//        if (order == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(orderMapper.toOrderDto(order),HttpStatus.OK);
        return cartDtoMono.flatMap(cartDto ->
            orderService.createOrder(cartDto, null))
                .map(orderMapper::toOrderDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }


    @Override
    @GetMapping()
    public Mono<ResponseEntity<Flux<OrderDto>>> listOrders(final ServerWebExchange exchange) {
//        List<OrderDto> orders = new ArrayList<>(orderMapper.toOrderDtos(orderService.getAllOrders()));
//        return new ResponseEntity<>(orders, HttpStatus.OK);
        return Mono.just(ResponseEntity.ok(orderService.getAllOrders().map(orderMapper::toOrderDto)));
    }

    @Override
    @GetMapping("/{orderId}")
    public Mono<ResponseEntity<OrderDto>> showOrderById(@PathVariable("orderId") Integer orderId, final ServerWebExchange exchange) {

//        Order order = orderService.getOrder(orderId);
//        if (order == null) {
//            return ResponseEntity.notFound().build();
//        }
//        OrderDto orderDto = orderMapper.toOrderDto(order);
//        return ResponseEntity.ok(orderDto);
        return orderService.getOrder(orderId)
                .map(orderMapper::toOrderDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
