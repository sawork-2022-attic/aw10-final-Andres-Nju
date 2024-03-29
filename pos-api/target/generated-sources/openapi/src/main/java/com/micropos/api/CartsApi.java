/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.micropos.api;

import com.micropos.dto.CartDto;
import com.micropos.dto.CartItemDto;
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
@Tag(name = "carts", description = "the carts API")
public interface CartsApi {

    /**
     * POST /carts/{cartId} : Add an item to cart
     *
     * @param cartId The id of the cart to retrieve (required)
     * @param cartItemDto The details of the item. (required)
     * @return Expected response to a valid request (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "addItemToCart",
        summary = "Add an item to cart",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  CartDto.class))),
            @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDto.class)))
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/carts/{cartId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default Mono<ResponseEntity<CartDto>> addItemToCart(
        @Parameter(name = "cartId", description = "The id of the cart to retrieve", required = true, schema = @Schema(description = "")) @PathVariable("cartId") Integer cartId,
        @Parameter(name = "CartItemDto", description = "The details of the item.", required = true, schema = @Schema(description = "")) @Valid @RequestBody Mono<CartItemDto> cartItemDto,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"id\" : 0, \"items\" : [ { \"amount\" : 1, \"product\" : { \"image\" : \"image\", \"price\" : 5.962133916683182, \"name\" : \"name\", \"id\" : \"id\" }, \"id\" : 6 }, { \"amount\" : 1, \"product\" : { \"image\" : \"image\", \"price\" : 5.962133916683182, \"name\" : \"name\", \"id\" : \"id\" }, \"id\" : 6 } ] }";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
        }
        return result.then(Mono.empty());

    }


    /**
     * POST /carts/{cartId}/checkout : checkout cart, generate an order
     *
     * @param cartId The id of the cart to checkout (required)
     * @return Expected response to a valid request (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "checkOut",
        summary = "checkout cart, generate an order",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  OrderDto.class))),
            @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDto.class)))
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/carts/{cartId}/checkout",
        produces = { "application/json" }
    )
    default Mono<ResponseEntity<OrderDto>> checkOut(
        @Parameter(name = "cartId", description = "The id of the cart to checkout", required = true, schema = @Schema(description = "")) @PathVariable("cartId") Integer cartId,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"id\" : 0, \"time\" : \"time\", \"items\" : [ { \"amount\" : 1, \"product\" : { \"image\" : \"image\", \"price\" : 5.962133916683182, \"name\" : \"name\", \"id\" : \"id\" }, \"id\" : 6 }, { \"amount\" : 1, \"product\" : { \"image\" : \"image\", \"price\" : 5.962133916683182, \"name\" : \"name\", \"id\" : \"id\" }, \"id\" : 6 } ] }";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
        }
        return result.then(Mono.empty());

    }


    /**
     * POST /carts : Create a new cart
     *
     * @param cartDto An empty cart (required)
     * @return The cart was successfully added. (status code 201)
     *         or Bad request. (status code 400)
     *         or Cart not found. (status code 404)
     *         or Server error. (status code 500)
     */
    @Operation(
        operationId = "createCart",
        summary = "Create a new cart",
        tags = { "carts" },
        responses = {
            @ApiResponse(responseCode = "201", description = "The cart was successfully added.", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  CartDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "Cart not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Server error.", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDto.class)))
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/carts",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default Mono<ResponseEntity<CartDto>> createCart(
        @Parameter(name = "CartDto", description = "An empty cart", required = true, schema = @Schema(description = "")) @Valid @RequestBody Mono<CartDto> cartDto,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"id\" : 0, \"items\" : [ { \"amount\" : 1, \"product\" : { \"image\" : \"image\", \"price\" : 5.962133916683182, \"name\" : \"name\", \"id\" : \"id\" }, \"id\" : 6 }, { \"amount\" : 1, \"product\" : { \"image\" : \"image\", \"price\" : 5.962133916683182, \"name\" : \"name\", \"id\" : \"id\" }, \"id\" : 6 } ] }";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
        }
        return result.then(Mono.empty());

    }


    /**
     * GET /carts : List all carts
     *
     * @return An array of carts (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "listCarts",
        summary = "List all carts",
        tags = { "carts" },
        responses = {
            @ApiResponse(responseCode = "200", description = "An array of carts", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  CartDto.class))),
            @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDto.class)))
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/carts",
        produces = { "application/json" }
    )
    default Mono<ResponseEntity<Flux<CartDto>>> listCarts(
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"id\" : 0, \"items\" : [ { \"amount\" : 1, \"product\" : { \"image\" : \"image\", \"price\" : 5.962133916683182, \"name\" : \"name\", \"id\" : \"id\" }, \"id\" : 6 }, { \"amount\" : 1, \"product\" : { \"image\" : \"image\", \"price\" : 5.962133916683182, \"name\" : \"name\", \"id\" : \"id\" }, \"id\" : 6 } ] }";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
        }
        return result.then(Mono.empty());

    }


    /**
     * GET /carts/{cartId} : Info for a specific cart
     *
     * @param cartId The id of the cart to retrieve (required)
     * @return Expected response to a valid request (status code 200)
     */
    @Operation(
        operationId = "showCartById",
        summary = "Info for a specific cart",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  CartDto.class)))
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/carts/{cartId}",
        produces = { "application/json" }
    )
    default Mono<ResponseEntity<CartDto>> showCartById(
        @Parameter(name = "cartId", description = "The id of the cart to retrieve", required = true, schema = @Schema(description = "")) @PathVariable("cartId") Integer cartId,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"id\" : 0, \"items\" : [ { \"amount\" : 1, \"product\" : { \"image\" : \"image\", \"price\" : 5.962133916683182, \"name\" : \"name\", \"id\" : \"id\" }, \"id\" : 6 }, { \"amount\" : 1, \"product\" : { \"image\" : \"image\", \"price\" : 5.962133916683182, \"name\" : \"name\", \"id\" : \"id\" }, \"id\" : 6 } ] }";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
        }
        return result.then(Mono.empty());

    }


    /**
     * GET /carts/{cartId}/total : Total for a specific cart
     *
     * @param cartId The id of the cart to get total (required)
     * @return Expected response to a valid request (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "showCartTotal",
        summary = "Total for a specific cart",
        tags = { "cart" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  Double.class))),
            @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDto.class)))
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/carts/{cartId}/total",
        produces = { "application/json" }
    )
    default Mono<ResponseEntity<Double>> showCartTotal(
        @Parameter(name = "cartId", description = "The id of the cart to get total", required = true, schema = @Schema(description = "")) @PathVariable("cartId") Integer cartId,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

}
