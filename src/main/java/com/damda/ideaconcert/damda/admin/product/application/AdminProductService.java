package com.damda.ideaconcert.damda.admin.product.application;

import java.util.List;

import com.damda.ideaconcert.damda.product.application.ProductReadDto;
import com.damda.ideaconcert.damda.product.application.ProductRequestDto;

import org.springframework.data.domain.Pageable;

public interface AdminProductService {
    void create(ProductRequestDto productRequest);
    List<ProductReadDto> read(Pageable page);
    void update(ProductRequestDto productRequest);
	void deleteById(int id);
}
