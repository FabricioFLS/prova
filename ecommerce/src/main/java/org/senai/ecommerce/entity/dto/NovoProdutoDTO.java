package org.senai.ecommerce.entity.dto;

import lombok.Data;

@Data
public class NovoProdutoDTO {
    private Long codigo;
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;
}
