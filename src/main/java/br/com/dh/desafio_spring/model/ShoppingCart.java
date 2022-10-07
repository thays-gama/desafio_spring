package br.com.dh.desafio_spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Log4j2
public class ShoppingCart {
    private int id;
    private List<Ticket> tickets;
    private BigDecimal total;

    public void sumTickets(){
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < tickets.size(); i++) {
            result = result.add(tickets.get(i).getTotal());
        }
        log.printf(Level.ERROR, result.toString());
        setTotal(result);
    }
}
