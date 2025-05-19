package com.damda.ideaconcert.damda.product.representation;

import java.util.List;

import com.damda.ideaconcert.damda.product.application.ProductReadDto;

import org.springframework.data.domain.Sort;

import lombok.Value;

@Value
public class UserProductReadDto {
    List<ProductReadDto> productList;
    Integer totalPage;
    Integer pageNumber;
    Integer size;
    Sort sort;
}
