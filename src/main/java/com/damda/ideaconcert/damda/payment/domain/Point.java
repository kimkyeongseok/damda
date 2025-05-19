package com.damda.ideaconcert.damda.payment.domain;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name ="user_point")
public class Point {
    @Id
    private Integer userPk;
    private Integer point;
    private String store;
    private String orderId;
    private Instant paymentDate;


    public Point(
        Integer userPk,
        Integer point,
        String store,
        String orderId
    ){
        this.userPk = userPk;
        this.point = point;
        this.store = store; 
        this.orderId = orderId;
        this.paymentDate = Instant.now();
    }
    public void update(Point userPoint) {
        this.userPk = userPoint.getUserPk();
        this.point += userPoint.getPoint();
        this.store = userPoint.getStore(); 
        this.orderId = userPoint.getOrderId();
    }

    public void purchase(int purchasePoint) {
        this.point -= purchasePoint;
    }
}
