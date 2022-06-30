package com.micropos.cart.service;

import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.dto.OrderDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface CartService {

    //Mono<Double> checkTotal(Cart cart);

    Mono<Double> checkTotal(Integer cartId);

    Mono<OrderDto> checkOut(Cart cart);

    Mono<OrderDto> checkOut(Integer cartId);

    Mono<Cart> add(Cart cart, Item item);

    Flux<Cart> getAllCarts();

    Mono<Cart> getCart(Integer cartId);

    Integer test();

    public Mono<Cart> addCart(Cart cart);

    public Cart getCartByID(Integer cardId);

}
