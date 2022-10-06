package br.com.dh.desafio_spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Client {
    private int clientId;
    private String name;
    private String lastName;
    private String email;
    private String state;
}
