package com.micropos.delivery.repository;

import com.micropos.delivery.model.Entry;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DeliveryRepository<Entry, Integer> {
    Flux<Entry> findAll();
    Mono<Entry> findEntryById(int id);
    Mono<com.micropos.delivery.model.Entry> save(com.micropos.delivery.model.Entry entry);
}
