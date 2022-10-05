package br.com.dh.desafio_spring.controller;

import br.com.dh.desafio_spring.model.Article;
import br.com.dh.desafio_spring.service.IArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private IArticle articleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveArticle(@RequestBody Article article){
        articleService.save(article);
        //articleService.save(article);
    }
}
