package com.sample.controller;

import com.sample.dto.request.ProductCreationRequest;
import com.sample.dto.request.ProductUpdateRequest;
import com.sample.dto.response.ProductResponse;
import com.sample.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.json.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j(topic = "PRODUCT-CONTROLLER")
@Tag(name = "Product Controller")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Add new product", description = "Return product ID")
    @PostMapping(path = "/add", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public String createProduct(@Valid @RequestBody ProductCreationRequest request) {
        return productService.addProduct(request);
    }

    @Operation(summary = "Update product", description = "Return no content")
    @PutMapping(path = "/upd", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(ACCEPTED)
    public void updateProduct(@Valid @RequestBody ProductUpdateRequest request) {
        productService.updateProduct(request);
    }

    @Operation(summary = "Delete product", description = "Return no content")
    @DeleteMapping(path = "/del/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    public void deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
    }

    @Operation(summary = "Get product detail", description = "Return product detail")
    @GetMapping(path = "/detail/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ProductResponse getProduct(@PathVariable("id") String id) {
        return productService.getProduct(id);
    }

    @Operation(summary = "Get product list", description = "Return list of products")
    @GetMapping(path = "/list", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public List<ProductResponse> getProducts(@RequestParam(defaultValue = "true") boolean status) {
        return productService.getProducts(status);
    }

    @Operation(summary = "Search product", description = "Return list of products")
    @GetMapping(path = "/search", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public List<ProductResponse> searchProduct(@RequestParam(defaultValue = "true") String name) {
        return productService.searchProduct(name);
    }
}
