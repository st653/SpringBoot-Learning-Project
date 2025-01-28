package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Artikel {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("articleName")
    private String articleName;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("weight")
    private Double weight;

    public Artikel(String articleName, Double price, Integer stock, Double weight) {
        this.articleName = articleName;
        this.price = price;
        this.stock = stock;
        this.weight = weight;
    }

}
