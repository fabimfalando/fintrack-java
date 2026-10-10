package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    private static final String URL = "jdbc:sqlite:fintrack.db";

    public static Connection conectar() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);

            // Cria a tabela se ainda não existir
            String sql = "CREATE TABLE IF NOT EXISTS transacoes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descricao TEXT NOT NULL, " +
                    "valor REAL NOT NULL, " +
                    "tipo TEXT NOT NULL, " +
                    "data TEXT NOT NULL);";

            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco: " + e.getMessage());
        }
        return conn;
    }

    public static void inicializarBanco() {
    }
}