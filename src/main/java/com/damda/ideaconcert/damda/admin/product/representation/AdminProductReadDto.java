package com.damda.ideaconcert.damda.admin.product.representation;

import java.util.List;

import com.damda.ideaconcert.damda.product.application.ProductReadDto;

import org.springframework.data.domain.Sort;

import lombok.Value;

@Value
public class AdminProductReadDto {
    List<ProductReadDto> productList;
    Integer totalPage;
    Integer pageNumber;
    Integer size;
    Sort sort;
}
