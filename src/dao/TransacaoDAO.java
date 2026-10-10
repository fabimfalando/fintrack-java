package dao;

import model.Transacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class TransacaoDAO {

    // CREATE (Adicionar à base de dados)
    public void adicionar(Transacao transacao) {
        // Os pontos de interrogação (?) são os moldes do PreparedStatement.
        // Eles protegem o sistema contra injeção de SQL (hackers).
        String sql = "INSERT INTO transacoes (descricao, valor, tipo, data) VALUES (?, ?, ?, ?)";

        // O try-with-resources abre a ligação e fecha automaticamente no fim, garantindo segurança
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Preenchemos os '?' com os dados exatos do seu objeto Transacao
            stmt.setString(1, transacao.getDescricao());
            stmt.setDouble(2, transacao.getValor());
            stmt.setString(3, transacao.getTipo());
            stmt.setString(4, transacao.getData().toString()); // Guarda a data em formato de texto (ISO)

            stmt.executeUpdate(); // Executa o comando de gravação!
            System.out.println("Transação guardada na base de dados com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao guardar transação: " + e.getMessage());
        }
    }

    // READ (Ler da base de dados)
    public List<Transacao> listarTodas() {
        List<Transacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacoes";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                String tipo = rs.getString("tipo");
                String dataStr = rs.getString("data");

                // Converte a data do texto para LocalDate de forma segura
                LocalDate data;
                if (dataStr != null && !dataStr.trim().isEmpty()) {
                    try {
                        data = LocalDate.parse(dataStr);
                    } catch (Exception e) {
                        data = LocalDate.now(); // Usa a data de hoje se o formato for inválido
                    }
                } else {
                    data = LocalDate.now();
                }

                // Cria o objeto Transacao e adiciona à lista
                Transacao t = new Transacao(id, descricao, valor, tipo, data);
                lista.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar transações: " + e.getMessage());
        }

        return lista;
    }

    public void salvar(Transacao transacao) {
        String sql = "INSERT INTO transacoes (descricao, valor, tipo, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transacao.getDescricao());
            stmt.setDouble(2, transacao.getValor());
            stmt.setString(3, transacao.getTipo());
            stmt.setString(4, transacao.getData().toString());

            stmt.executeUpdate();
            System.out.println("Transação guardada no banco com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao guardar no banco: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM transacoes WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Transação com ID " + id + " removida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar transação: " + e.getMessage());
        }
    }
}