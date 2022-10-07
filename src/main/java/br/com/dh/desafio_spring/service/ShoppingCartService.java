package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.model.ShoppingCart;
import br.com.dh.desafio_spring.model.Ticket;
import br.com.dh.desafio_spring.repository.ShoppingCartRepo;
import br.com.dh.desafio_spring.repository.TicketRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Classe que representa o Service do Carrinho de Compras
 * @author thays-gama
 */
@Service
public class ShoppingCartService implements IShoppingCart{

    private final ShoppingCartRepo repo;
    private final TicketRepo ticketRepo;
    private Integer counter;

    /**
     * Construtor da classe
     * @param repo
     * @param ticketRepo
     */
    public ShoppingCartService(ShoppingCartRepo repo, TicketRepo ticketRepo) {
        this.repo = repo;
        this.ticketRepo = ticketRepo;
        this.counter = repo.getAll().size();
    }

    /**
     * @param ticketId Id dos tickets que devem ser associados ao carinhos
     * @return Carrinho de compras salvo com a lista de tickets inseridos
     */
    @Override
    public ShoppingCart save(Integer[] ticketId) {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<Ticket> ticketList = new ArrayList<>();

        Arrays.stream(ticketId).forEach(id -> {
            Optional<Ticket> ticket = ticketRepo.findById(id);
            if(ticket.isPresent())
                ticketList.add(ticket.get());
        });

        shoppingCart.setTickets(ticketList);
        shoppingCart.sumTickets();
        shoppingCart.setId(++counter);
        return repo.saveShoppingCart(shoppingCart);
    }

    /**
     * @return lista de todos os carrinhos de compra cadastrados
     */
    @Override
    public List<ShoppingCart> findAll() {
        return repo.getAll();
    }

    /**
     * @param id id do carrinho que deve ser deletado
     */
    @Override
    public void deleteById(Integer id) {
        repo.removeById(id);
    }
}
