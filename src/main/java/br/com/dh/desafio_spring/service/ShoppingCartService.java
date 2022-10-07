package br.com.dh.desafio_spring.service;

import br.com.dh.desafio_spring.exception.AlreadyExistingException;
import br.com.dh.desafio_spring.exception.NotAuthorized;
import br.com.dh.desafio_spring.exception.NotFoundException;
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

/**
 * Classe que representa o Service do Carrinho de Compras
 * @author thays-gama
 */
@Service
public class ShoppingCartService implements IShoppingCart{

    private final ShoppingCartRepo repo;
    private final TicketRepo ticketRepo;
    private final ClientRepo clientRepo;
    private Integer counter;

    /**
     * Construtor da classe
     * @param repo
     * @param ticketRepo
     */
    public ShoppingCartService(ShoppingCartRepo repo, TicketRepo ticketRepo, ClientRepo clientRepo) {
        this.repo = repo;
        this.ticketRepo = ticketRepo;
        this.counter = repo.getAll().size();
        this.clientRepo = clientRepo;
    }

    private void validateExistingTicket(Integer id) {
        List<ShoppingCart> shoppingcCarts = this.findAll();

        shoppingcCarts.stream().forEach((shoppingCart) -> {
            shoppingCart.getTickets().stream().forEach((ticket) -> {
                if (ticket.getId() == id) {
                    throw new AlreadyExistingException("Não é possível efetuar uma compra com um ticket já finalizado");
                }
            });
        });
    }

    private void verifySameCustomerOnTicket(Integer id, Integer clientId) {
        Optional<Ticket> ticket = ticketRepo.findById(id);

        if (ticket.isPresent() && ticket.get().getId() != clientId) {
            throw new NotAuthorized("Não é possível efetuar uma compra com um ticket de outro cliente");
        }
    }

    /**
     * @param ticketId Id dos tickets que devem ser associados ao carinhos
     * @return Carrinho de compras salvo com a lista de tickets inseridos
     */
    @Override
    public ShoppingCart save(Integer[] ticketId, Integer clientId) {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<Ticket> ticketList = new ArrayList<>();
        Optional<Client> client = clientRepo.findById(clientId);
        //checar se cliente existe

        if (client.isEmpty()) {
            throw new NotFoundException("Não existe um cliente com o id " +clientId);
        }

        Arrays.stream(ticketId).forEach(id -> {
            Optional<Ticket> ticket = ticketRepo.findById(id);
            if (ticket.isPresent()) {
                validateExistingTicket(id);
                verifySameCustomerOnTicket(id, clientId);
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

    /**
     * @return lista de todos os carrinhos de compra cadastrados
     */
    @Override
    public List<ShoppingCart> findAll() {
        List<ShoppingCart> shoppingCarts = repo.getAll();

        if (shoppingCarts.isEmpty()) {
            throw new NotFoundException("Carrinho de compras não encontrado");
        }

        return shoppingCarts;
    }

    /**
     * @param id id do carrinho que deve ser deletado
     */
    @Override
    public void deleteById(Integer id) {
        repo.removeById(id);
    }
}
