package com.br.stockcontrol.stockcontrol;

import com.br.stockcontrol.stockcontrol.Domain.Produto;
import com.br.stockcontrol.stockcontrol.Repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProdutoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void testSalvarProduto() {
        // Cria um produto para armazenar no banco de dados
        Produto produto = new Produto("1", "Produto Teste", 10L, LocalDate.now(), LocalDate.now().plusMonths(1), 50.0);

        // Salva o produto no banco de dados usando o repositório
        Produto produtoSalvo = produtoRepository.save(produto);

        // Verifica se o produto foi salvo corretamente
        assertThat(produtoSalvo.getId()).isNotNull();
        assertThat(produtoSalvo.getNome()).isEqualTo("Produto Teste");

        // Exclui o produto após o teste
        produtoRepository.deleteById(produtoSalvo.getId());

        // Verifica se o produto foi excluído corretamente
        assertThat(produtoRepository.findById(produtoSalvo.getId())).isEmpty();
    }
}

