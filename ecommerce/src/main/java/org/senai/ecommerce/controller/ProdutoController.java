package org.senai.ecommerce.controller;
import io.swagger.v3.oas.annotations.Operation;
import org.senai.ecommerce.entity.Produto;
import org.senai.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    @Operation(summary = "buscar todos os produtos")
    @GetMapping
    public ResponseEntity<?> getProdutos() {
        return new ResponseEntity<>(produtoService.getProdutos(), HttpStatus.OK);
    }
    @Operation(summary = "buscar um produto pelo codigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<?> getProdutoPorCodigo(@PathVariable Long codigo) {
        return new ResponseEntity<>(produtoService.getProdutoPorCodigo(codigo), HttpStatus.OK);
    }
    @Operation(summary = "criar um novo produto")
    @PostMapping("/criarproduto")
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {
        return new ResponseEntity<>(produtoService.criarProduto(produto), HttpStatus.CREATED);
    }
    @Operation(summary = "atualizar um produto pelo codigo")
    @PutMapping("/{codigo}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long codigo, @RequestBody Produto productDetails) {
        return new ResponseEntity<>(produtoService.atualizarProduto(codigo, productDetails), HttpStatus.OK);
    }

    @Operation(summary = "deletar um produto pelo codigo")
    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long codigo) {
        produtoService.excluirProduto(codigo);
        return new ResponseEntity<>("Produto Excluído do Sucesso!", HttpStatus.OK);
    }
}
