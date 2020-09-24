package com.bestcommerce.product.service;

import com.bestcommerce.product.dto.FilterDto;
import com.bestcommerce.product.entity.Product;

import java.util.List;

public interface  ProductService {
    List<Product> findAllByUserId(int id, FilterDto filterDto);
    List<Product> findAllOrderBy(int id, FilterDto filterDto);
    Product save(int merchantId,Product product);
    Product update(int merchantId,int productId,Product product);

}
