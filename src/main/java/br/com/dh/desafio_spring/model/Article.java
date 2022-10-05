package br.com.dh.desafio_spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private int productId;
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private int quantity;
    private boolean freeShipping;
    private String prestige;
}
