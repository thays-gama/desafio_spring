package br.com.dh.desafio_spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private int id;
    private List<Article> articles;
    private BigDecimal total;

    public void addArticle(Article article){
        verifyArticles();
        articles.add(article);
        sumArticle();
    }

    public void removeArticle(Article article){
        verifyArticles();
        articles.remove(article);
        sumArticle();
    }

    public void removeArticle(int id){
        verifyArticles();
        articles.remove(id);
        sumArticle();
    }

    public void sumArticle(){
        verifyArticles();
        final BigDecimal[] result = {BigDecimal.ZERO};
        final Double[] test2 = {0.0};
        articles.forEach(article -> {
            BigDecimal test = article.getPrice();
            test2[0] += Double.parseDouble(String.valueOf(result[0].add(test.multiply(new BigDecimal(article.getQuantity())))));
        });
        this.total = BigDecimal.valueOf(test2[0]);
    }

    public void clearTicket(){
        articles.clear();
    }

    private void verifyArticles(){
        if(articles == null){
            articles = new ArrayList<>();
        }
    }
}
