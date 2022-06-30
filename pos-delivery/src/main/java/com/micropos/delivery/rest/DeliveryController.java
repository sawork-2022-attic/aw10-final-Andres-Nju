package com.micropos.delivery.rest;

import com.micropos.api.DeliveryApi;
import com.micropos.delivery.mapper.DeliveryMapper;
import com.micropos.delivery.service.DeliveryService;
import com.micropos.dto.DeliveryEntryDto;
import com.micropos.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController implements DeliveryApi {

    private DeliveryMapper deliveryMapper;

    private DeliveryService deliveryService;

    @Autowired
    public void setDeliveryMapper(DeliveryMapper deliveryMapper) { this.deliveryMapper = deliveryMapper; }

    @Autowired
    public void setDeliveryService(DeliveryService deliveryService) { this.deliveryService = deliveryService; }

    @Override
    @GetMapping()
    public Mono<ResponseEntity<Flux<DeliveryEntryDto>>> listDelivery(final ServerWebExchange exchange) {
//        List<DeliveryEntryDto> entries = new ArrayList<>(deliveryMapper.toEntryDtos(deliveryService.getAllEntries()));
//        return new ResponseEntity<>(entries, HttpStatus.OK);
        return Mono.just(ResponseEntity.ok(deliveryService.getAllEntries().map(deliveryMapper::toEntryDto)));
    }

    @Override
    @PostMapping()
    public Mono<ResponseEntity<DeliveryEntryDto>> createDelivery(@RequestBody Mono<OrderDto> orderDtoMono, final ServerWebExchange exchange){
//        DeliveryEntryDto entryDto = deliveryMapper.toEntryDto(deliveryService.createEntry(orderDto));
//        return new ResponseEntity<>(entryDto, HttpStatus.OK);
        return orderDtoMono.flatMap(orderDto ->
                        deliveryService.createEntry(orderDto))
                .map(deliveryMapper::toEntryDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
