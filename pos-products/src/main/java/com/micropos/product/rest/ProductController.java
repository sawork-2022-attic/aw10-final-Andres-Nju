package com.micropos.product.rest;

import com.micropos.api.ProductsApi;
import com.micropos.dto.ProductDto;
import com.micropos.product.mapper.ProductMapper;
import com.micropos.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController implements ProductsApi {

    private final ProductMapper productMapper;

    private final ProductService productService;


    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @Override
    @GetMapping()
    public Mono<ResponseEntity<Flux<ProductDto>>> listProducts(final ServerWebExchange exchange){
//        List<ProductDto> products = new ArrayList<>(productMapper.toProductsDto(this.productService.products()));
//        if (products.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(products, HttpStatus.OK);
        return Mono.just(ResponseEntity.ok(productService.products().map(productMapper::toProductDto)));
    }

    @Override
    @GetMapping(value = "{productId}")
    public Mono<ResponseEntity<ProductDto>> showProductById(String productId, final ServerWebExchange exchange) {
//        ProductDto product = productMapper.toProductDto(this.productService.getProduct(productId));
//        if (product == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(product, HttpStatus.OK);
        return productService.getProduct(productId)
                .map(productMapper::toProductDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
