package com.micropos.cart.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropos.cart.mapper.CartMapper;
import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.cart.repository.CartRepository;
import com.micropos.dto.CartDto;
import com.micropos.dto.OrderDto;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    private final String COUNTER_URL = "http://POS-COUNTER/counter";

    private final String ORDER_URL = "http://POS-ORDERS/orders";

    private CartMapper cartMapper;

    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    private Double check(Cart cart) {
        double total = 0;
        for (Item item : cart.getItems()){
            total += item.unitPrice() * item.quantity();
        }
        return total;
    }

    public Integer test() {

        Integer test = restTemplate.getForObject(COUNTER_URL + "/test", Integer.class);
        return test;
    }

    @Override
    public Mono<Double> checkTotal(Integer cartId) {
        return cartRepository.findCartById(cartId)
                .map(cart -> check((Cart) cart))
                .defaultIfEmpty(Double.NaN);
    }

    @Override
    public Mono<Cart> add(Cart cart, Item item) {
        if (cart.addItem(item))
            //return cartRepository.save(cart);
            return Mono.just(cart);
        return null;
    }

    @Override
    public Flux<Cart> getAllCarts() {
        return cartRepository.findAllCarts();
    }

    @Override
    public Mono<Cart> getCart(Integer cartId) {
        return cartRepository.findCartById(cartId);
    }

    @Override
    public Mono<Cart> addCart(Cart cart) {
        return cartRepository.saveCart(cart);
    }

    @Override
    public Mono<OrderDto> checkOut(Cart cart){
        CartDto cartDto = cartMapper.toCartDto(cart);
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = null;
        try {
            request = new HttpEntity<>(mapper.writeValueAsString(cartDto), headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        OrderDto orderDto = restTemplate.postForObject(ORDER_URL, request, OrderDto.class);
        cartRepository.DeleteCart(cart);
        return Mono.just(orderDto);
    }

    @Override
    public Mono<OrderDto> checkOut(Integer cartId){
        Cart cart = (Cart) this.cartRepository.getCartByID(cartId);

        if (cart == null) return null;
        return checkOut(cart);
    }

    public Cart getCartByID(Integer cardId){
        return (Cart) cartRepository.getCartByID(cardId);
    }
}
