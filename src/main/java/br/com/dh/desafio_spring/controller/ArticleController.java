package br.com.dh.desafio_spring.controller;

import br.com.dh.desafio_spring.model.Article;
import br.com.dh.desafio_spring.service.IArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
    @Autowired
    private IArticle articleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveArticle(@RequestBody Article article){
        articleService.save(article);
    }

    @GetMapping(params = {"category", "freeShipping"})
    @ResponseStatus(HttpStatus.OK)
    public List<Article> findAllByCategoryAndFreeShipping(@RequestParam String category, @RequestParam Boolean freeShipping){
        return articleService.findAllByCategoryAndFreeShipping(category, freeShipping);
    }

    @GetMapping(params = {"freeShipping", "prestige"})
    @ResponseStatus(HttpStatus.OK)
    public List<Article> findAllByFreeShippingAndPrestige(@RequestParam Boolean freeShipping, @RequestParam String prestige){
        return articleService.findAllByFreeShippingAndPrestige(freeShipping, prestige);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles(){
        return new ResponseEntity<>(articleService.getAll(), HttpStatus.OK);
    }

    @GetMapping(params = {"category"})
    @ResponseStatus(HttpStatus.OK)
    public List<Article>findByCategory(@RequestParam String category){
        return articleService.findByCategory(category);
    }
}
