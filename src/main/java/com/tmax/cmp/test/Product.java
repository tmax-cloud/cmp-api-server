package com.tmax.cmp.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue // Auto IncId Generator
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = true)
    private Integer price;

    private String memo;

    @Builder
    public Product(String name, Integer price, String memo) {
        this.name = name;
        this.price = price;
        this.memo = memo;
    }
}