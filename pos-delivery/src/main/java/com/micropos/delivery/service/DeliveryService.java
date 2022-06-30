package com.micropos.delivery.service;

import com.micropos.delivery.model.Entry;
import com.micropos.dto.OrderDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {

    Mono<Entry> createEntry(OrderDto orderDto);

    Flux<Entry> getAllEntries();

    Mono<Entry> getEntry(Integer orderId);
}
