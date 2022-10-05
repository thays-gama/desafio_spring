package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.model.Article;

import java.util.List;

public interface IArticle {
    void save(Article article);
    List<Article> getAll();
    List<Article> findAllByCategoryAndFreeShipping(String category, Boolean freeShipping);

    List<Article> findAllByFreeShippingAndPrestige(Boolean freeShipping, String prestige);
    List<Article> findAllByCategoryAndFreeShippingOrdered(String category, Boolean freeShipping, int order);
}
