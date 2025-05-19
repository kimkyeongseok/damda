package com.damda.ideaconcert.damda.product.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer>{
    List<Product> findAllByOrderByUploadDateDesc();
    List<Product> findByIsFreeOrderByUploadDateDesc(boolean isFree);
}
