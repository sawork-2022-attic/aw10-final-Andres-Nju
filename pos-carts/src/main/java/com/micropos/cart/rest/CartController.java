package com.micropos.cart.rest;

import com.micropos.api.CartsApi;
import com.micropos.cart.mapper.CartMapper;
import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.cart.service.CartService;
import com.micropos.dto.CartDto;
import com.micropos.dto.CartItemDto;
import com.micropos.dto.OrderDto;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/carts")
@EnableDiscoveryClient
public class CartController implements CartsApi {

    private final CartService cartService;
    private final CartMapper cartMapper;

    public CartController(CartService cartService, CartMapper cartMapper) {
        this.cartMapper = cartMapper;
        this.cartService = cartService;
    }

    @Override
    @PostMapping("/{cartId}")
    public Mono<ResponseEntity<CartDto>> addItemToCart(@PathVariable("cartId") Integer cartId, @RequestBody Mono<CartItemDto> cartItemDto, final ServerWebExchange exchange) {
//        Cart cart = cartService.getCart(cartId);
//        if(cart == null)
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        CartDto cartDto = cartMapper.toCartDto(cart);
//        Item item = cartMapper.toItem(cartItemDto, cartDto);
//        System.out.println(item);
//        Cart ret = cartService.add(cart, item);
//        if(ret == null)
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        cartDto.addItemsItem(cartItemDto);
//        return new ResponseEntity<>(cartDto, HttpStatus.OK);
        Cart cart = cartService.getCartByID(cartId);
        if (cart == null)
            return Mono.empty();
        return cartItemDto.map(cartMapper::toItem)
                .flatMap(item -> cartService.add(cart, item))
                .map(cartMapper::toCartDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping()
    public Mono<ResponseEntity<CartDto>> createCart(@RequestBody Mono<CartDto> cartDto, final ServerWebExchange exchange) {
//        Cart cart = cartMapper.toCart(cartDto);
//        if(cart == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        cart = cartService.addCart(cart);
//        if (cart == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(cartMapper.toCartDto(cart),HttpStatus.OK);
        return cartDto.map(cartMapper::toCart)
                .flatMap(cartService::addCart)
                .map(cartMapper::toCartDto)
                .map(ResponseEntity::ok);
    }

    @Override
    @GetMapping()
    public Mono<ResponseEntity<Flux<CartDto>>> listCarts(ServerWebExchange exchange) {
//        List<CartDto> carts = new ArrayList<>(cartMapper.toCartDtos(this.cartService.getAllCarts()));
//        if (carts.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(carts, HttpStatus.OK);
//        List<CartDto> carts = new ArrayList<>(cartMapper.toCartDtos(this.cartService.getAllCarts()));
//        if (carts.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(carts, HttpStatus.OK);
        return Mono.just(ResponseEntity.ok(cartService.getAllCarts().map(cartMapper::toCartDto)));
    }

    @Override
    @GetMapping("/{cartId}")
    public Mono<ResponseEntity<CartDto>> showCartById(@PathVariable("cartId") Integer cartId, final ServerWebExchange exchange) {
//        Cart cart = cartService.getCart(cartId);
//        if(cart == null)
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        CartDto cartDto = cartMapper.toCartDto(cart);
//        return new ResponseEntity<>(cartDto, HttpStatus.OK);
        return cartService.getCart(cartId)
                .map(cartMapper::toCartDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping("/{cartId}/total")
    public Mono<ResponseEntity<Double>> showCartTotal(@PathVariable("cartId") Integer cartId, final ServerWebExchange exchange) {

//        Double total = cartService.checkTotal(cartId);
//
//        if (total == -1d) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(total);
        return cartService.checkTotal(cartId)
                .map(ResponseEntity::ok);
    }

    @Override
    @PostMapping("/{cartId}/checkout")
    public Mono<ResponseEntity<OrderDto>> checkOut(@PathVariable("cartId") Integer cartId, final ServerWebExchange exchange) {

//        OrderDto orderDto = cartService.checkOut(cartId);
//        if (orderDto == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(orderDto, HttpStatus.OK);
        return cartService.checkOut(cartId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

}
