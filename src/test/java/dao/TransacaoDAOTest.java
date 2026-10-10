package dao;

import model.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransacaoDAOTest {

    private TransacaoDAO dao;

    @BeforeEach
    public void setUp() {
        dao = new TransacaoDAO();
    }

    @Test
    public void testSalvarEListarTransacao() {
        Transacao t = new Transacao("Teste JUnit", 200.0, "RECEITA", LocalDate.now());
        dao.salvar(t);

        List<Transacao> todas = dao.listarTodas();
        assertNotNull(todas);
        assertFalse(todas.isEmpty());

        boolean encontrada = todas.stream().anyMatch(tr -> "Teste JUnit".equals(tr.getDescricao()));
        assertTrue(encontrada);
    }
}