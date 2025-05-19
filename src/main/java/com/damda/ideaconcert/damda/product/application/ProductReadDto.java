package com.damda.ideaconcert.damda.product.application;

import java.time.Instant;
import java.util.List;

import lombok.Value;

@Value
public class ProductReadDto {
    Integer id;
    String name;
    String category;
    Boolean isFree;
    String preview;
    Integer price;
    Instant uploadDate;
    List<ElementReadDto> elements;
}

