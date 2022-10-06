package br.com.dh.desafio_spring.dto;

import br.com.dh.desafio_spring.model.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {
    private String fullName;
    private String state;

    public ClientDTO(Client client) {
        this.fullName = client.getName() +" "+ client.getLastName();
        this.state = client.getState();
    }
}
