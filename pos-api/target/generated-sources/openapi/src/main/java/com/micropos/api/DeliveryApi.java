/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.micropos.api;

import com.micropos.dto.DeliveryEntryDto;
import com.micropos.dto.ErrorDto;
import com.micropos.dto.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.codec.multipart.Part;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-24T19:44:02.460524800+08:00[GMT+08:00]")
@Validated
@Tag(name = "delivery", description = "the delivery API")
public interface DeliveryApi {

    /**
     * POST /delivery : create a delivery
     *
     * @param orderDto An order. (required)
     * @return The delivery was successfully created. (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "createDelivery",
        summary = "create a delivery",
        tags = { "delivery" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The delivery was successfully created.", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  DeliveryEntryDto.class))),
            @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDto.class)))
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/delivery",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default Mono<ResponseEntity<DeliveryEntryDto>> createDelivery(
        @Parameter(name = "OrderDto", description = "An order.", required = true, schema = @Schema(description = "")) @Valid @RequestBody Mono<OrderDto> orderDto,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"orderId\" : 0, \"status\" : \"status\" }";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
        }
        return result.then(Mono.empty());

    }


    /**
     * GET /delivery : List all delivery
     *
     * @return A paged array of delivery (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "listDelivery",
        summary = "List all delivery",
        tags = { "delivery" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of delivery", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  DeliveryEntryDto.class))),
            @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDto.class)))
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/delivery",
        produces = { "application/json" }
    )
    default Mono<ResponseEntity<Flux<DeliveryEntryDto>>> listDelivery(
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"orderId\" : 0, \"status\" : \"status\" }";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
        }
        return result.then(Mono.empty());

    }

}
