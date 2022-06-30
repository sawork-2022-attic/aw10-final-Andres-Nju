package com.micropos.delivery.repository;

import com.micropos.delivery.model.Entry;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


public class DeliveryRepositoryImpl implements DeliveryRepository{

    private List<Entry> entries = new ArrayList<>();

    @Override
    public Flux<Entry> findAll(){
        return Flux.fromIterable(entries);
    }

    @Override
    public Mono<Entry> findEntryById(int id){
        for (Entry entry : entries) {
            if (entry.getOrderId() == id) {
                return Mono.just(entry);
            }
        }
        return Mono.empty();
    }

    @Override
    public Mono<Entry> save(Entry entry){
        entry.setOrderId(entries.size());
        if (entries.add(entry)) {
            return Mono.just(entry);
        }
        return Mono.empty();
    }
}
