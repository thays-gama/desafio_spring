package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.model.Client;
import br.com.dh.desafio_spring.model.ShoppingCart;
import br.com.dh.desafio_spring.model.Ticket;
import br.com.dh.desafio_spring.repository.ClientRepo;
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
    private final ClientRepo clientRepo;
    private Integer counter;

    public ShoppingCartService(ShoppingCartRepo repo, TicketRepo ticketRepo, ClientRepo clientRepo) {
        this.repo = repo;
        this.ticketRepo = ticketRepo;
        this.counter = repo.getAll().size();
        this.clientRepo = clientRepo;
    }

    @Override
    public ShoppingCart save(Integer[] ticketId, Integer clientId) {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<Ticket> ticketList = new ArrayList<>();
        Optional<Client> client = clientRepo.findById(clientId);
        //checar se cliente existe

        Arrays.stream(ticketId).forEach(id -> {
            Optional<Ticket> ticket = ticketRepo.findById(id);
            if(ticket.isPresent()){
                ticketList.add(ticket.get());
                //checar se o ticket que esta sendo salvo é do mesmo cliente que quer salvar carrinho
                //checar se o ticket foi salvo em outro carrinho
            }
        });

        shoppingCart.setClient(client.get());
        shoppingCart.setTickets(ticketList);
        shoppingCart.sumTickets();
        shoppingCart.setId(++counter);
        return repo.saveShoppingCart(shoppingCart);
    }

    @Override
    public List<ShoppingCart> findAll() {
        return repo.getAll();
    }

    @Override
    public void deleteById(Integer id) {
        repo.removeById(id);
    }
}
