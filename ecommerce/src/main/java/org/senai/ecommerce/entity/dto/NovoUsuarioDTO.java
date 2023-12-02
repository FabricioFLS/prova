package org.senai.ecommerce.entity.dto;

import lombok.Data;

@Data
public class NovoUsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private String permissao;
}

