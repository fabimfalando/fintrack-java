package repository;

import model.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RepositorioGenericoTest {

    private RepositorioGenerico<Transacao> repo;

    @BeforeEach
    public void setUp() {
        repo = new RepositorioGenerico<>();
    }

    @Test
    public void testAdicionarERemoverElemento() {
        Transacao t = new Transacao("Teste", 100.0, "RECEITA", LocalDate.now());
        repo.adicionar(t);

        assertEquals(1, repo.tamanho());
        assertTrue(repo.remover(t));
        assertEquals(0, repo.tamanho());
    }

    @Test
    public void testAdicionarTodosComCuringaExtends() {
        List<Transacao> lista = new ArrayList<>();
        lista.add(new Transacao("Item 1", 50.0, "RECEITA", LocalDate.now()));
        lista.add(new Transacao("Item 2", 30.0, "DESPESA", LocalDate.now()));

        repo.adicionarTodos(lista);
        assertEquals(2, repo.tamanho());
    }
}
