package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.model.Article;
import br.com.dh.desafio_spring.repository.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleService implements IArticle{
    @Autowired
    private ArticleRepo repo;

    @Override
    public void save(Article article) {
        repo.saveArticle(article);
    }

    @Override
    public List<Article> getAll() {
        return repo.getAll();
    }

    @Override
    public List<Article> findAllByCategoryAndFreeShipping(String category, Boolean freeShipping) {
        return repo.findAllByCategoryAndFreeShipping(category, freeShipping);
    }

    @Override
    public List<Article> findAllByFreeShippingAndPrestige(Boolean freeShipping, String prestige) {
        return repo.findAllByFreeShippingAndPrestige(freeShipping,prestige);
    }

}
