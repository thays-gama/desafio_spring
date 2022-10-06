package br.com.dh.desafio_spring.repository;

import br.com.dh.desafio_spring.model.Article;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ArticleRepo {
    private final String linkFile = "src/main/resources/articles.json";
    private ObjectMapper mapper = new ObjectMapper();


    public Optional<Article> saveArticle(Article article){
        List<Article> articles = new ArrayList<>(getAll());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        articles.add(article);

        try{
            writer.writeValue(new File(linkFile), articles);

            return Optional.of(article);
        } catch (Exception ex){
            System.out.println("Erro ao salvar os dados!");
        }

        return Optional.empty();
    }

    public List<Article> getAll(){
        try{
            return Arrays.asList(mapper.readValue(new File(linkFile), Article[].class));
        } catch (Exception ex){
            System.out.println("Erro ao ler o arquivo");
        }

        return null;
    }

    public List<Article> findAllByCategoryAndFreeShipping(String category, Boolean freeShipping){
        List<Article> articles = new ArrayList<>(getAll());
        return articles.stream()
                .filter(item-> item.getCategory().equalsIgnoreCase(category) && item.isFreeShipping() == freeShipping)
                .collect(Collectors.toList());
    }

    public List<Article> findAllByFreeShippingAndPrestige(Boolean freeShipping, String prestige) {
        List<Article> articles = new ArrayList<>(getAll());
        return articles.stream()
                .filter(item-> item.isFreeShipping() == freeShipping && item.getPrestige().equals(prestige))
                .collect(Collectors.toList());
    }

    public List<Article> findByCategory(String category){
        List<Article> articles = new ArrayList<>(getAll());
        return articles.stream()
                .filter(item->item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public Article getArticleById(int productId){
        return getAll().get(productId - 1);
    }
}
