package model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TransacaoTest {

    @Test
    public void testCriacaoTransacaoValida() {
        LocalDate data = LocalDate.of(2026, 10, 9);
        Transacao t = new Transacao("Salário", 3000.0, "RECEITA", data);

        assertEquals("Salário", t.getDescricao());
        assertEquals(3000.0, t.getValor());
        assertEquals("RECEITA", t.getTipo());
        assertEquals(data, t.getData());
    }

    @Test
    public void testTipoEmMaiusculas() {
        Transacao t = new Transacao("Mercado", 150.0, "despesa", LocalDate.now());
        assertEquals("DESPESA", t.getTipo());
    }
}