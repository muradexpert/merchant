package com.bestcommerce.product.controller;

import com.bestcommerce.product.dto.FilterDto;
import com.bestcommerce.product.entity.Product;
import com.bestcommerce.product.service.ProductService;
import com.bestcommerce.product.util.JwtTokenPayload;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestHeader HttpHeaders header,@Valid @RequestBody Product product){
        int merchantId= JwtTokenPayload.getMerchantIdFromToken(header);
        return new ResponseEntity<>(productService.save(merchantId,product),HttpStatus.CREATED);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<?> updateProduct(@Valid @PathVariable(name = "id") int productId,@RequestHeader HttpHeaders header, @Valid @RequestBody Product product){
        int merchantId= JwtTokenPayload.getMerchantIdFromToken(header);
        return new ResponseEntity<>(productService.update(merchantId,productId,product),HttpStatus.CREATED);
    }


    @GetMapping("/getAllByUser")
    public ResponseEntity<?> getAllProducts(@RequestHeader HttpHeaders header,@Valid FilterDto filterDto){
        int merchantId= JwtTokenPayload.getMerchantIdFromToken(header);
        return new ResponseEntity<>(productService.findAllByUserId(merchantId, filterDto), HttpStatus.OK);
    }

    @GetMapping("/getAllOrderBy")
    public ResponseEntity<?> getAllProductsOrderBy(@RequestHeader HttpHeaders header,@Valid FilterDto filterDto){
        int merchantId= JwtTokenPayload.getMerchantIdFromToken(header);
        return new ResponseEntity<>(productService.findAllOrderBy(merchantId, filterDto), HttpStatus.OK);
    }



}
