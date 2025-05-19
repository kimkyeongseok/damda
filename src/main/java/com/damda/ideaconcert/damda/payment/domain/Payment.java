package com.damda.ideaconcert.damda.payment.domain;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.damda.ideaconcert.damda.product.domain.Product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(indexes = {@Index(columnList = "userPk")}, name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderId;
    private Integer userPk;
    private Instant date;
    @OneToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Payment(
        String orderId,
        Integer userPk,
        Product product
    ){
        this.orderId = orderId;
        this.userPk = userPk;      
        this.product = product;
        this.date = Instant.now();
    }
}

