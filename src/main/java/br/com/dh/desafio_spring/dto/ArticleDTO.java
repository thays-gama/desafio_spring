package br.com.dh.desafio_spring.dto;

import br.com.dh.desafio_spring.model.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleDTO {
    private int productId;
    private String name;
    private int quantity;

    public ArticleDTO(Article article) {
        this.productId = article.getProductId();
        this.name = article.getName();
        this.quantity = article.getQuantity();
    }
}
