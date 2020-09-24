package com.bestcommerce.product.service;

import com.bestcommerce.product.dto.FilterDto;
import com.bestcommerce.product.entity.Product;
import com.bestcommerce.product.exception.ProductNotFoundException;
import com.bestcommerce.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Logger log = LoggerFactory.getLogger(ProductService.class);
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllByUserId(int id, FilterDto filterDto) {
        Pageable pageable=PageRequest.of(filterDto.getPage(), filterDto.getSize());
        int count=5;
        return productRepository.findAllByUserIdAndInventoryGreaterThan(id,count,pageable);
    }

    @Override
    public List<Product> findAllOrderBy(int merchantId, FilterDto filterDto) {

        String[] sortType= filterDto.getSortType().split("_");
        int inventory=5;


        Pageable pageable=PageRequest.of(filterDto.getPage(), filterDto.getSize(),
                Sort.by(sortType[1].equalsIgnoreCase("asc")?Sort.Direction.ASC:Sort.Direction.DESC,sortType[0]));
        return productRepository.findAllByUserIdAndInventoryGreaterThan(merchantId,inventory,pageable);
    }

    @Override
    public Product save(int merchantId,Product product) {
        product.setUserId(merchantId);
        return productRepository.save(product);
    }

    @Override
    public Product update(int merchantId,int productId, Product product) {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(!optionalProduct.isPresent()){
           throw new ProductNotFoundException("Product not found");
        }
        product.setId(optionalProduct.get().getId());
        product.setUserId(merchantId);
        return productRepository.save(product);
    }


}
