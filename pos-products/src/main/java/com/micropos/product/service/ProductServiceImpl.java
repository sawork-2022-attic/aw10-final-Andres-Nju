package com.micropos.product.service;

import com.micropos.product.model.Product;
import com.micropos.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<Product> products() {
        return Flux.fromIterable(productRepository.allProducts());
    }

    @Override
    public Mono<Product> getProduct(String id) {
        return Mono.just(productRepository.findProduct(id));
    }

    @Override
    public Product randomProduct() {
        return null;
    }
}
