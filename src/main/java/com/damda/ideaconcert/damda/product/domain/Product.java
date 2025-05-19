package com.damda.ideaconcert.damda.product.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String category;
    private Boolean isFree;
    private String preview;
    private Integer price;
    private Instant uploadDate;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Element> elements;

    public Product(
        String name, 
        String category, 
        Boolean isFree, 
        String preview, 
        Integer price
    ){
        this.name = name;
        this.category = category;
        this.isFree = isFree;
        this.preview = preview;
        this.price = price;
        this.uploadDate = Instant.now();
        this.elements = new ArrayList<>();
    };
    public void addElement(Element element){
        this.elements.add(element);
        element.setProduct(this);
    }

    public void update(Product updateProduct){
        this.name = updateProduct.getName();
        this.category = updateProduct.getCategory();
        this.isFree = updateProduct.getIsFree();
        this.preview = updateProduct.getPreview();
        this.price = updateProduct.getPrice();
    }

	// public void deleteAllElements() {   
    //     for(Element element : this.elements) {
    //         element.setProduct(null);
    //         this.elements.remove(element);
    //     }
    // }
}
