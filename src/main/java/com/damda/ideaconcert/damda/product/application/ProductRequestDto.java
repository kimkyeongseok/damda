package com.damda.ideaconcert.damda.product.application;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private Integer id;
    private String name;
    private String category;
    private Boolean isFree;
    private List<MultipartFile> img;
    private Integer price;
}