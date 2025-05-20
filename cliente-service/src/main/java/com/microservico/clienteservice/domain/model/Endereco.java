package com.microservico.clienteservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Embeddable  // A classe Endereco não terá uma tabela separada, ela será embutida em Cliente
public class Endereco {

    private String rua;
    private String cidade;
    private String estado;
    private String cep;
}
