package com.bestcommerce.product.repository;

import com.bestcommerce.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
    List<Product> findAllByUserIdAndInventoryGreaterThan(int userId,int count,Pageable pageable);


}
