package com.br.stockcontrol.stockcontrol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexaoBancoDados {

    private static final String URL = "jdbc:postgresql://localhost:1234/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "Admin";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

            if (conexao != null) {
                System.out.println("Conexão bem-sucedida com o banco de dados PostgreSQL!");
                conexao.close(); 
            } else {
                System.out.println("Falha ao conectar-se ao banco de dados PostgreSQL.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar-se ao banco de dados PostgreSQL: " + e.getMessage());
        }
    }
}

