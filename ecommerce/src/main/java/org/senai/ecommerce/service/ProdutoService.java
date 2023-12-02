package org.senai.ecommerce.service;

import org.senai.ecommerce.entity.Produto;
import org.senai.ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService{

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoPorCodigo(Long codigo) {
        Optional<Produto> produto = produtoRepository.findById(codigo);
        return produto.orElse(null);
    }

    public Produto criarProduto(Produto produto) {
        produtoRepository.save(produto);
        return produto;
    }

    public Produto atualizarProduto(Long codigo, Produto produtoAlteracao) {
        produtoRepository.findById(codigo)
                    .map(produto -> {
                    produto.setNome(produtoAlteracao.getNome());
                    produto.setDescricao(produtoAlteracao.getDescricao());
                    produto.setPreco(produtoAlteracao.getPreco());
                    produto.setEstoque(produtoAlteracao.getEstoque());
                    return produtoRepository.save(produto);
                }).orElse(null);
        return produtoAlteracao;
    }

    public void excluirProduto(Long codigo) {
            produtoRepository.deleteById(codigo);
    }
}
