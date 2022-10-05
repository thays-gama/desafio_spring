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
        articles.stream().forEach(article -> this.total.add(article.getPrice()));
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
