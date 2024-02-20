package com.br.stockcontrol.stockcontrol.Service;

import com.br.stockcontrol.stockcontrol.Domain.Produto;
import com.br.stockcontrol.stockcontrol.PostgresDBConfig;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
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

                System.out.println("Quantidade: " + produto.getQuantidade());

                Long quantidade = produto.getQuantidade();
                if (quantidade != null) {
                    ps.setLong(2, quantidade);
                } else {
                    ps.setNull(2, Types.BIGINT);
                }

                System.out.println("Data de Adição: " + produto.getDataDeAdicao());

                LocalDate dataAdicao = produto.getDataDeAdicao() != null ? produto.getDataDeAdicao() : LocalDate.now();
                ps.setDate(3, java.sql.Date.valueOf(dataAdicao));

                System.out.println("Data de Validade: " + produto.getDataDeValidade());

                // Definindo a data de validade
                LocalDate dataValidade = produto.getDataDeValidade();
                if (dataValidade != null) {
                    ps.setDate(4, java.sql.Date.valueOf(dataValidade));
                } else {
                    ps.setNull(4, Types.DATE);
                }

                System.out.println("Valor: " + produto.getValor());

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

    private void removerProduto(String id) {
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

    public List<Produto> listarProdutosInventario() {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "SELECT nome, data_adicao FROM produto";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Produto produto = new Produto (
                                rs.getString("nome"),
                                rs.getDate("data_adicao").toLocalDate()
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

    public List<Produto> listarProdutosEstoque() {
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

    public void alterarQuantidadeProduto(String id, int novaQuantidade) {
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "UPDATE produto SET quantidade = ? WHERE id = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setInt(1, novaQuantidade);
                ps.setString(2, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerProdutoPorNome(String nome) {
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "DELETE FROM produto WHERE nome = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, nome);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarQuantidadeProdutoPorNome(String nome, int novaQuantidade) {
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "UPDATE produto SET quantidade = ? WHERE nome = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setInt(1, novaQuantidade);
                ps.setString(2, nome);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerProdutoPorId(String id) {
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "DELETE FROM produto WHERE id = CAST(? AS INTEGER)";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> obterQuantidadeProdutosProximosFim() {
        List<Integer> quantidadeProdutos = new ArrayList<>();

        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "SELECT COUNT(*) AS quantidade FROM produto WHERE data_validade <= CURRENT_DATE + INTERVAL '30 days'";

            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int quantidade = rs.getInt("quantidade");
                        quantidadeProdutos.add(quantidade);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantidadeProdutos;
    }

    public List<Integer> obterValorRetirado() {
        List<Integer> valorRetirado = new ArrayList<>();

        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "SELECT produto_id, SUM(valor_retirado) AS valor_total FROM transacoes_retirada GROUP BY produto_id";

            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int valorTotal = rs.getInt("valor_total");
                        valorRetirado.add(valorTotal);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valorRetirado;
    }

    public Map<String, Map<String, Double>> listarNomeQuantidadeValorProdutos() {
        Map<String, Map<String, Double>> produtos = new HashMap<>();
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "SELECT nome, quantidade, valor FROM produto";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String nome = rs.getString("nome");
                        double quantidade = rs.getDouble("quantidade");
                        double valor = rs.getDouble("valor");

                        Map<String, Double> detalhes = new HashMap<>();
                        detalhes.put("quantidade", quantidade);
                        detalhes.put("valor", valor);

                        produtos.put(nome, detalhes);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public Map<String, Integer> listarNomeQuantidadeProdutos() {
        Map<String, Integer> nomeQuantidadeMap = new HashMap<>();
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "SELECT nome, quantidade FROM produto";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String nome = rs.getString("nome");
                        int quantidade = rs.getInt("quantidade");
                        nomeQuantidadeMap.put(nome, quantidade);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomeQuantidadeMap;
    }

    public Map<String, Double> listarNomeValorProdutos() {
        Map<String, Double> nomeValorMap = new HashMap<>();
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "SELECT nome, valor FROM produto";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String nome = rs.getString("nome");
                        double valor = rs.getDouble("valor");
                        nomeValorMap.put(nome, valor);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomeValorMap;
    }

    public boolean atualizarQuantidade(Long id, int quantidade) {
        try (Connection conexao = PostgresDBConfig.obterConexao()) {
            String sql = "UPDATE produto SET quantidade = ? WHERE id = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setInt(1, quantidade);
                ps.setLong(2, id);
                int linhasAfetadas = ps.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
