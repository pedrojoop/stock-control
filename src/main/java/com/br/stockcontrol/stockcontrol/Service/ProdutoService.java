package com.br.stockcontrol.stockcontrol.Service;

import com.br.stockcontrol.stockcontrol.Domain.Produto;
import com.br.stockcontrol.stockcontrol.PostgresDBConfig;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProdutoService {

    public List<Produto> listarTodosProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "SELECT * FROM produto";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Produto produto = new Produto (
                                rs.getString("id"),
                                rs.getString("nome"),
                                rs.getLong("quantidade"),
                                rs.getDate("data_adicao").toLocalDate(),
                                rs.getDate("data_validade").toLocalDate(),
                                rs.getDouble("valor")
                        );
                        produtos.add(produto);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public Produto salvarProduto(Produto produto) {
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "INSERT INTO produto (nome, quantidade, data_adicao, data_validade, valor) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, produto.getNome());

                // Log para verificar a quantidade
                System.out.println("Quantidade: " + produto.getQuantidade());

                // Definindo a quantidade
                Long quantidade = produto.getQuantidade();
                if (quantidade != null) {
                    ps.setLong(2, quantidade);
                } else {
                    ps.setNull(2, Types.BIGINT);
                }

                // Log para verificar a data de adição
                System.out.println("Data de Adição: " + produto.getDataDeAdicao());

                // Definindo a data de adição
                LocalDate dataAdicao = produto.getDataDeAdicao() != null ? produto.getDataDeAdicao() : LocalDate.now();
                ps.setDate(3, java.sql.Date.valueOf(dataAdicao));

                // Log para verificar a data de validade
                System.out.println("Data de Validade: " + produto.getDataDeValidade());

                // Definindo a data de validade
                LocalDate dataValidade = produto.getDataDeValidade();
                if (dataValidade != null) {
                    ps.setDate(4, java.sql.Date.valueOf(dataValidade));
                } else {
                    ps.setNull(4, Types.DATE);
                }

                // Log para verificar o valor
                System.out.println("Valor: " + produto.getValor());

                // Definindo o valor
                Double valor = produto.getValor();
                if (valor != null) {
                    ps.setDouble(5, valor);
                } else {
                    ps.setNull(5, Types.DOUBLE);
                }

                int linhasAfetadas = ps.executeUpdate();
                if (linhasAfetadas > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        produto.setId(rs.getString(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }


    public Optional<AtomicReference<Produto>> encontrarProdutoPorId(String id) {
        AtomicReference<Produto> produto = null;
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "SELECT * FROM produto WHERE id = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        produto.set(new Produto(
                                rs.getString("id"),
                                rs.getString("nome"),
                                rs.getLong("quantidade"),
                                rs.getDate("data_adicao").toLocalDate(),
                                rs.getDate("data_validade").toLocalDate(),
                                rs.getDouble("valor")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(produto);
    }

    public void removerProduto(String id) {
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "DELETE FROM produto WHERE id = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
