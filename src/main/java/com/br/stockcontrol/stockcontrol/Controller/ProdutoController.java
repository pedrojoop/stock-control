package com.br.stockcontrol.stockcontrol.Controller;

import com.br.stockcontrol.stockcontrol.Domain.Produto;
import com.br.stockcontrol.stockcontrol.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<String> removerProduto(@PathVariable String id) {
        try {
            produtoService.removerProdutoPorId(id);
            return new ResponseEntity<>("Produto removido com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erro ao remover produto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> adicionarProduto(@RequestBody Produto produto) {
        try {
            produtoService.salvarProduto(produto);
            return new ResponseEntity<>("Produto adicionado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erro ao adicionar produto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/inventario")
    public List<Produto> listarProdutosInventario() {
        return produtoService.listarProdutosInventario();
    }

    @GetMapping("/estoque")
    public List<Produto> listarProdutosEstoque() {
        return produtoService.listarProdutosEstoque();
    }

    @PostMapping("/{nome}/alterar-quantidade")
    public ResponseEntity<String> alterarQuantidadeProduto(@PathVariable String nome, @RequestParam int novaQuantidade) {
        try {
            produtoService.alterarQuantidadeProdutoPorNome(nome, novaQuantidade);
            return new ResponseEntity<>("Quantidade do produto alterada com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erro ao alterar quantidade do produto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quantidade-produtos-proximos-fim")
    public List<Integer> obterQuantidadeProdutosProximosFim() {
        List<Integer> quantidadeProdutos = produtoService.obterQuantidadeProdutosProximosFim();
        return quantidadeProdutos;
    }

    @GetMapping("/valor-retirado")
    public List<Integer> obterValorRetirado() {
        List<Integer> valorRetirado = produtoService.obterValorRetirado();
        return valorRetirado;
    }

    @GetMapping("/produtos-nome-quantidade")
    public ResponseEntity<Map<String, Integer>> listarNomeQuantidadeProdutos() {
        Map<String, Integer> produtos = produtoService.listarNomeQuantidadeProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/produtos-nome-valor")
    public ResponseEntity<Map<String, Double>> listarNomeValorProdutos() {
        Map<String, Double> produtos = produtoService.listarNomeValorProdutos();
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}/atualizar-quantidade")
    public ResponseEntity<Void> atualizarQuantidadeProduto(@PathVariable Long id, @RequestParam int quantidade) {
        boolean sucesso = produtoService.atualizarQuantidade(id, quantidade);
        if (sucesso) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

