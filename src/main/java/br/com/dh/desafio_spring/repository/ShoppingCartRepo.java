package br.com.dh.desafio_spring.repository;

import br.com.dh.desafio_spring.model.ShoppingCart;
import br.com.dh.desafio_spring.model.Ticket;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que representa o Repository do Carrinho de Compras
 * @author thays-gama
 */
@Repository
@Log4j2
public class ShoppingCartRepo {
    private final String linkFile = "src/main/resources/shoppingCarts.json";
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * @param shoppingCart carrinho de compras
     * @return carrinho salvo
     * @since 1.0
     */
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<ShoppingCart> shoppingCarts = getAll();

        shoppingCarts.add(shoppingCart);

        try {
            writer.writeValue(new File(linkFile), shoppingCarts);
        } catch (Exception ex) {
            log.printf(Level.WARN, "Erro ao salvar os dados!");
        }
        log.printf(Level.INFO, "Novo carrinho salvo.");
        return shoppingCart;
    }

    /**
     * @param id id do carrinho a ser removido
     * @since 1.0
     */
    public void removeById(Integer id) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<ShoppingCart> shoppingCarts = getAll();
        shoppingCarts = shoppingCarts.stream().filter(shoppingCart -> shoppingCart.getId()!=id)
                .collect(Collectors.toList());
        try {
            writer.writeValue(new File(linkFile), shoppingCarts);
        } catch (Exception ex) {
            log.printf(Level.WARN, "Erro ao salvar os dados!");
        }
        log.printf(Level.INFO, "Carrinho com id "+id+" deletado.");
    }

    /**
     * @return lista de todos osa carrinhos salvos
     * @since 1.0
     */
    public List<ShoppingCart> getAll(){
        try{
            log.printf(Level.INFO, "Arquivo "+linkFile+" carregado");
            return new ArrayList<>(Arrays.asList(mapper.readValue(new File(linkFile), ShoppingCart[].class)));
        } catch (Exception ex){
            log.printf(Level.WARN, "Erro ao ler o arquivo. O arquivo "+linkFile+" não foi carregado");
        }
        return Collections.emptyList();
    }
}
