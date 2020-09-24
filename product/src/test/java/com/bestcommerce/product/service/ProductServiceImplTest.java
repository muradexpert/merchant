package com.bestcommerce.product.service;

import com.bestcommerce.product.dto.FilterDto;
import com.bestcommerce.product.entity.Category;
import com.bestcommerce.product.entity.Payment;
import com.bestcommerce.product.entity.Product;
import com.bestcommerce.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ProductServiceImplTest {
    private  List<Product> products;
    private   Product product;
    private  Category category;
    private  Payment payment;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService ;





    @BeforeEach
    public void setUp(){
        products=new ArrayList<>();
          category=Category.builder().id(1).name("Phone").build();

          payment=Payment.builder().id(1).type("Direct ").build();
        product=Product.builder().id(1).userId(1).name("Note 9")
                .description("Samsung Note 9").unit(120)
                .price(999.99).inventory(10).categories(Collections.singletonList(category))
                .payments(Collections.singletonList(payment)).build();
        products.add(product);
    }



    @Test
    void findAllByUserId() {
        int userId=1;
        int inventoryCount=5;
        Pageable pageable= PageRequest.of(0,1);
        FilterDto filterDto = FilterDto.builder().page(0).size(1).build();
         when(productRepository.findAllByUserIdAndInventoryGreaterThan(userId,inventoryCount,pageable)).thenReturn(products);

        // Method call
        List<Product> foundProducts = productService.findAllByUserId(userId, filterDto);

        // Verification
        assertEquals(foundProducts.get(0).getName(),"Note 9");
         verify(productRepository, times(1)).findAllByUserIdAndInventoryGreaterThan(userId,inventoryCount,pageable);
          verifyNoMoreInteractions(productRepository);
    }

    @Test
    void findAllOrderBy() {



        int userId=1;
        int inventoryCount=5;

        Product product=Product.builder().id(1).userId(1).name("A8")
                .description("Samsung Note 9").unit(120)
                .price(799.99).inventory(10).categories(Collections.singletonList(category))
                .payments(Collections.singletonList(payment)).build();

        products.add(product);

        Pageable pageable= PageRequest.of(0,1, Sort.by(Sort.Direction.DESC,"price"));
        FilterDto filterDto = FilterDto.builder().page(0).size(1).sortType("price_DESC").build();
         when(productRepository.findAllByUserIdAndInventoryGreaterThan(userId,inventoryCount,pageable)).thenReturn(products);

        // Method call
        List<Product> foundProducts = productService.findAllOrderBy(userId, filterDto);

        // Verification
        assertEquals(foundProducts.get(0).getName(),"Note 9");
         verify(productRepository,  times(1)).findAllByUserIdAndInventoryGreaterThan(userId,inventoryCount,pageable);
         verifyNoMoreInteractions(productRepository);
    }

    @Test
    void save() {
        int userId=1;
      when(productRepository.save(product)).thenReturn(product);

      Product foundProduct=productService.save(userId,product);
      assertEquals(foundProduct,product);
      verify(productRepository,times(1)).save(product);
      verifyNoMoreInteractions(productRepository);

    }

    @Test
    void update() {
        int userId=1;

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product foundProduct=productService.update(userId,product.getId(),product);
        assertEquals(foundProduct,product);
        verify(productRepository,times(1)).findById(product.getId());
        verify(productRepository,times(1)).save(product);
        verifyNoMoreInteractions(productRepository);

    }
}