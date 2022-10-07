package br.com.dh.desafio_spring.repository;

import br.com.dh.desafio_spring.exception.*;
import br.com.dh.desafio_spring.model.Article;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ArticleRepo {
    private final String linkFile = "src/main/resources/articles.json";
    private ObjectMapper mapper = new ObjectMapper();



    public Optional<List<Article>> saveArticle(List<Article> newArticles) throws ServerException {
        List<Article> articles = new ArrayList<>(getAll());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        for (Article article : newArticles) {
            checkArticle(article);
            existsArticle(article, articles);
            articles.add(article);
        }

        try{
            writer.writeValue(new File(linkFile), articles);

            return Optional.of(newArticles);
        } catch (Exception ex){
            throw new ServerException("Erro ao salvar os dados");
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

        List<String> categorys = articles.stream().map(Article::getCategory).collect(Collectors.toList());

        if(!categorys.contains(category)) throw new NotFoundException("Categoria não encontrada!");

        return articles.stream()
                .filter(item->item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public void existsArticle(Article newArticle, List<Article> articles){
        for (Article article : articles) {
            if (article.getProductId() == newArticle.getProductId())
                throw new AlreadyExistingException("Produto já cadastrado!");
            if (article.getName().equalsIgnoreCase(newArticle.getName()) && article.getBrand()
                    .equalsIgnoreCase(newArticle.getBrand()))
                throw new AlreadyExistingException(" Nome e marca já cadastrados!");
        }
    }

    public void checkArticle (Article newArticle)  {
        String voidFields = "";
        if (newArticle.getProductId() <= 0)
            voidFields += "productId ";
        if (newArticle.getName() == null)
            voidFields += "name ";
        if (newArticle.getCategory() == null)
            voidFields += "category ";
        if (newArticle.getBrand() == null)
            voidFields += "brand ";
        if (newArticle.getPrice() == null || newArticle.getPrice().doubleValue()<=0)
            voidFields += "price ";
        if (newArticle.getQuantity() <= 0)
            voidFields += "quantity ";
        if (newArticle.getPrestige() == null )
            voidFields += "prestige ";
        if (!voidFields.isEmpty())
            throw new RequiredFieldException("Esse(s) campo(s) precisa(m) ser preeenchido(s): " + voidFields);

    }
    
    public Article getArticleById(int productId) {
        if(getAll().size() >= productId)
            return getAll().get(productId - 1);

        return null;
    }
}
