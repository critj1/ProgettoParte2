package Gestione;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvestimentoTest {
    private Investimento investimento;

    @BeforeEach
    void setUp() {
        investimento = new Investimento(1000.0, 3, 10, 80);
    }

    @Test
    void testAvanzaSettimana() {
        assertFalse(investimento.avanzaSettimana());
        assertFalse(investimento.avanzaSettimana());
        assertTrue(investimento.avanzaSettimana());
    }

    @Test
    void testGetGuadagno() {
        double guadagno = investimento.getGuadagno();
        assertTrue(guadagno >= 3 * 1000.0 * 0.9 && guadagno <= 3 * 1000.0 * 1.1);
    }

}
