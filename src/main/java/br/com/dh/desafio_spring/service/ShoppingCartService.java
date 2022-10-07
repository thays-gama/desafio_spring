package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.exception.NotFoundException;
import br.com.dh.desafio_spring.model.ShoppingCart;
import br.com.dh.desafio_spring.model.Ticket;
import br.com.dh.desafio_spring.repository.ShoppingCartRepo;
import br.com.dh.desafio_spring.repository.TicketRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService implements IShoppingCart{

    private final ShoppingCartRepo repo;
    private final TicketRepo ticketRepo;
    private Integer counter;

    public ShoppingCartService(ShoppingCartRepo repo, TicketRepo ticketRepo) {
        this.repo = repo;
        this.ticketRepo = ticketRepo;
        this.counter = repo.getAll().size();
    }

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

    @Override
    public List<ShoppingCart> findAll() {
        List<ShoppingCart> shoppingCarts = repo.getAll();

        if (shoppingCarts.isEmpty()) {
            throw new NotFoundException("Carrinho de compras não encontrado");
        }

        return shoppingCarts;
    }

    @Override
    public void deleteById(Integer id) {
        repo.removeById(id);
    }
}
