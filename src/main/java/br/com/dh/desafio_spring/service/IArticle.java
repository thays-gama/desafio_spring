package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.model.Article;

import java.util.List;

public interface IArticle {
    void save(Article article);
    List<Article> getAll();
}
