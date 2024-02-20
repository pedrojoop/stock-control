package com.br.stockcontrol.stockcontrol.Repository;

import com.br.stockcontrol.stockcontrol.Domain.Produto;
import com.br.stockcontrol.stockcontrol.PostgresDBConfig;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {

    public List<Produto> findAll() {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conexao = PostgresDBConfig.obterConexao();
             PreparedStatement ps = conexao.prepareStatement("SELECT * FROM produto");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getLong("quantidade"),
                        rs.getDate("data_adicao").toLocalDate(),
                        rs.getDate("data_validade").toLocalDate(),
                        rs.getDouble("valor")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public Optional<Produto> findById(String id) {
        try (Connection conexao = PostgresDBConfig.obterConexao();
             PreparedStatement ps = conexao.prepareStatement("SELECT * FROM produtos WHERE id = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto(
                            rs.getString("id"),
                            rs.getString("nome"),
                            rs.getLong("quantidade"),
                            rs.getDate("data_de_adicao").toLocalDate(),
                            rs.getDate("data_de_validade").toLocalDate(),
                            rs.getDouble("valor")
                    );
                    return Optional.of(produto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void deleteById(String id) {
        try (Connection conexao = PostgresDBConfig.obterConexao();
             PreparedStatement ps = conexao.prepareStatement("DELETE FROM produtos WHERE id = ?")) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produto save(Produto produto) {
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "INSERT INTO produtos (nome, quantidade, data_adicao, data_validade, valor) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, produto.getNome());
                ps.setLong(2, produto.getQuantidade());
                ps.setDate(3, Date.valueOf(produto.getDataDeAdicao()));
                ps.setDate(4, Date.valueOf(produto.getDataDeValidade()));
                ps.setDouble(5, produto.getValor());

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

    public Produto update(Produto produto) {
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "UPDATE produtos SET nome = ?, quantidade = ?, data_de_adicao = ?, " +
                    "data_de_validade = ?, valor = ? WHERE id = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, produto.getNome());
                ps.setLong(2, produto.getQuantidade());
                ps.setDate(3, Date.valueOf(produto.getDataDeAdicao()));
                ps.setDate(4, Date.valueOf(produto.getDataDeValidade()));
                ps.setDouble(5, produto.getValor());
                ps.setString(6, produto.getId());

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }
}
