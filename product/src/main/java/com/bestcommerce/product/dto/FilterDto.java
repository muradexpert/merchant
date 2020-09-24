package com.bestcommerce.product.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FilterDto {
    private int page;
    private int size;
    private String sortType;



}
