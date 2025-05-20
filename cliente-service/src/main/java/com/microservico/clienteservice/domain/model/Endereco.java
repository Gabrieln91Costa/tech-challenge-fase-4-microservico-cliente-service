package com.microservico.clienteservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  // ✅ Adicionado para resolver o erro
@Embeddable
public class Endereco {

    private String rua;
    private String cidade;
    private String estado;
    private String cep;
}
