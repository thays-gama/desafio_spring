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

@Repository
public class ArticleRepo {
    private final String linkFile = "src/main/resources/articles.json";
    private ObjectMapper mapper = new ObjectMapper();


    public void saveArticle(Article article){
        List<Article> articles = new ArrayList<>(getAll());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        articles.add(article);

        try{
            writer.writeValue(new File(linkFile), articles);
        } catch (Exception ex){
            System.out.println("Erro ao salvar os dados!");
        }
    }

    public List<Article> getAll(){
        try{
            return Arrays.asList(mapper.readValue(new File(linkFile), Article[].class));
        } catch (Exception ex){
            System.out.println("Erro ao ler o arquivo");
        }

        return null;
    }
}
