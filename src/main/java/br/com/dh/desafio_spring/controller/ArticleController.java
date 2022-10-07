package br.com.dh.desafio_spring.controller;

import br.com.dh.desafio_spring.dto.ArticleDTO;
import br.com.dh.desafio_spring.model.Article;
import br.com.dh.desafio_spring.service.IArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
    @Autowired
    private IArticle articleService;

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ArticleDTO>> saveArticle(@RequestBody List<Article> articles) throws ServerException {
        return new ResponseEntity<>(articleService.save(articles), HttpStatus.CREATED);
    }

    @GetMapping(params = {"category", "freeShipping", "order"})
    @ResponseStatus(HttpStatus.OK)
    public List<Article> findAllByCategoryAndFreeShippingOrdered(@RequestParam String category, @RequestParam Boolean freeShipping, @RequestParam int order){
        return articleService.findAllByCategoryAndFreeShippingOrdered(category, freeShipping, order);
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
