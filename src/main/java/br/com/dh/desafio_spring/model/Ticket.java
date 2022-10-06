package br.com.dh.desafio_spring.model;

import br.com.dh.desafio_spring.repository.ArticleRepo;
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
        updateInventory(article);
        sumArticle();
    }

    public void removeArticle(Article article){
        verifyArticles();
        articles.remove(article);
        updateInventory(article, article.getQuantity());
        sumArticle();
    }

    public void removeArticle(int id){
        verifyArticles();
        updateInventory(articles.get(id - 1), articles.get(id - 1).getQuantity());
        articles.remove(id - 1);
        sumArticle();
    }

    public void sumArticle(){
        verifyArticles();
        BigDecimal result = BigDecimal.ZERO;
        articles.forEach(article -> {
            result.add(article.getPrice().multiply(new BigDecimal(article.getQuantity())));
        });
        this.total = result;
    }

    public void updateInventory(Article article){
        ArticleRepo articleRepo = new ArticleRepo();
        Article articleOrigin = articleRepo.getArticleById(article.getProductId());

        if(articleOrigin.getQuantity() < article.getQuantity())
            return;

        articleOrigin.setQuantity(-article.getQuantity());
    }

    public void updateInventory(Article article, int quantity){
        ArticleRepo articleRepo = new ArticleRepo();
        Article articleOrigin = articleRepo.getArticleById(article.getProductId());

        articleOrigin.setQuantity(quantity);
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
