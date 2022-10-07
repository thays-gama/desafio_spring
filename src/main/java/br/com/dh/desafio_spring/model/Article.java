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

    public void setQuantity(int quantity){
        this.quantity += quantity;
    }

    public void applyQuantity(int quantity){
        this.quantity = quantity;
    }

    public boolean withStock(){
        return this.quantity > 0;
    }

    @Override
    public String toString() {
        return "Article{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", freeShipping=" + freeShipping +
                ", prestige='" + prestige + '\'' +
                '}';
    }
}
