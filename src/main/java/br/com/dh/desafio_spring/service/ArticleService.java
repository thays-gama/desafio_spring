package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.dto.ArticleDTO;
import br.com.dh.desafio_spring.model.Article;
import br.com.dh.desafio_spring.repository.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArticleService implements IArticle{
    @Autowired
    private ArticleRepo repo;

    @Override
    public ArticleDTO save(Article article) {
        return new ArticleDTO(repo.saveArticle(article).get());
    }

    @Override
    public List<Article> getAll() {
        return repo.getAll().stream().filter(Article::withStock).collect(Collectors.toList());
    }

    @Override
    public List<Article> findAllByCategoryAndFreeShipping(String category, Boolean freeShipping) {
        return repo.findAllByCategoryAndFreeShipping(category, freeShipping);
    }

    @Override
    public List<Article> findAllByFreeShippingAndPrestige(Boolean freeShipping, String prestige) {
        return repo.findAllByFreeShippingAndPrestige(freeShipping,prestige);
    }
    public List<Article> findAllByCategoryAndFreeShippingOrdered(String category, Boolean freeShipping, int order) {
        List<Article> articleList = repo.findAllByCategoryAndFreeShipping(category, freeShipping);
        switch(order){
            case 0:
                articleList.sort(Comparator.comparing(Article::getName));
                return articleList;
            case 1:
                articleList.sort(Comparator.comparing(Article::getName).reversed());
                return articleList;
            case 2:
                articleList.sort(Comparator.comparing(Article::getPrice).reversed());
                return articleList;
            case 3:
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
