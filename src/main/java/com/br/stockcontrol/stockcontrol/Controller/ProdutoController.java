package com.br.stockcontrol.stockcontrol.Controller;

import com.br.stockcontrol.stockcontrol.Domain.Produto;
import com.br.stockcontrol.stockcontrol.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listarTodosProdutos() {
        return produtoService.listarTodosProdutos();
    }

    @PostMapping
    public Produto salvarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @GetMapping("/{id}")
    public AtomicReference<Produto> encontrarProdutoPorId(@PathVariable String id) {
        return produtoService.encontrarProdutoPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void removerProduto(@PathVariable String id) {
        produtoService.removerProduto(id);
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> adicionarProduto(@RequestBody Produto produto) {
        try {
            // Salvar o produto no banco de dados
            produtoService.salvarProduto(produto);
            return new ResponseEntity<>("Produto adicionado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            // Log da exceção para identificar a causa do erro
            e.printStackTrace();
            return new ResponseEntity<>("Erro ao adicionar produto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
