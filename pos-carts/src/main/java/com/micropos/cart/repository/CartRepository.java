package com.micropos.cart.repository;

import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.dto.ProductDto;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CartRepository <Cart, Integer> {

    Flux<Cart> findAllCarts();
    Mono<Cart> findCartById(int id);
    Mono<com.micropos.cart.model.Cart> saveCart(com.micropos.cart.model.Cart cart);
    Flux<Item> findItemsOfCart(int id);
    boolean addItemToCart(int cartId, Item item);
    boolean ModifyItem(int cartId, Item item);
    boolean DeleteItem(int cartId, Item item);
    Cart getCartByID(int id);
    boolean DeleteCart(com.micropos.cart.model.Cart cart);
}
