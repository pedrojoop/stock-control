package com.br.stockcontrol.stockcontrol;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class PostgresDBConfig {

    private static final String URL = "jdbc:postgresql://localhost:1234/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "Admin";

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

