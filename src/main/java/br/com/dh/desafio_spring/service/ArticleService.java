package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.dto.ArticleDTO;
import br.com.dh.desafio_spring.exception.NotFoundException;
import br.com.dh.desafio_spring.model.Article;
import br.com.dh.desafio_spring.repository.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public class ArticleService implements IArticle {
    final int ASC_ALPHABETIC = 0;
    final int DESC_ALPHABETIC = 1;
    final int DESC_PRICE = 2;
    final int ASC_PRICE = 3;


    @Autowired
    private ArticleRepo repo;

    @Override
    public ArticleDTO save(Article article) {
        return new ArticleDTO(repo.saveArticle(article).get());
    }

    @Override
    public List<Article> getAll() {
        return repo.getAll();
    }

    @Override
    public List<Article> findAllByCategoryAndFreeShipping(String category, Boolean freeShipping) {
        List<Article> article = repo.findAllByCategoryAndFreeShipping(category, freeShipping);

        if (article.isEmpty()) {
            throw new NotFoundException("Objeto não encontrado");
        }

        return article;
    }

    @Override
    public List<Article> findAllByFreeShippingAndPrestige(Boolean freeShipping, String prestige) {
        List<Article> article = repo.findAllByFreeShippingAndPrestige(freeShipping, prestige);

        if (article.isEmpty()) {
            throw new NotFoundException("Objeto não encontrado");
        }

        return article;
    }

    public List<Article> findAllByCategoryAndFreeShippingOrdered(String category, Boolean freeShipping, int order) {
        List<Article> articleList = repo.findAllByCategoryAndFreeShipping(category, freeShipping);
        switch(order){
            case ASC_ALPHABETIC:
                articleList.sort(Comparator.comparing(Article::getName));
                return articleList;
            case DESC_ALPHABETIC:
                articleList.sort(Comparator.comparing(Article::getName).reversed());
                return articleList;
            case DESC_PRICE:
                articleList.sort(Comparator.comparing(Article::getPrice).reversed());
                return articleList;
            case ASC_PRICE:
                articleList.sort(Comparator.comparing(Article::getPrice));
                return articleList;
            default:
                return articleList;

        }
    }

    @Override
    public List<Article> findByCategory(String category){
        return repo.findByCategory(category);
    }

}
