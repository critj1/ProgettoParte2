package Gestione;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GestioneInvestimentiTest {
    private GestioneInvestimenti gestioneInvestimenti;
    private Investimento investimento1;
    private Investimento investimento2;

    @BeforeEach
    void setUp() {
        gestioneInvestimenti = new GestioneInvestimenti();
        investimento1 = new Investimento(1000.0, 2, 10, 80);
        investimento2 = new Investimento(500.0, 1, 30, 50);
        gestioneInvestimenti.aggiungiInvestimento(investimento1);
        gestioneInvestimenti.aggiungiInvestimento(investimento2);
    }

    @Test
    void testAggiungiInvestimento() {
        Investimento nuovoInvestimento = new Investimento(2000.0, 3, 45, 50);
        gestioneInvestimenti.aggiungiInvestimento(nuovoInvestimento);
        assertEquals(3, gestioneInvestimenti.getInvestimenti().size());
        assertTrue(gestioneInvestimenti.getInvestimenti().contains(nuovoInvestimento));
    }

    @Test
    void testAggiornaInvestimenti() {
        double guadagno1 = investimento1.getGuadagno();
        double guadagno2 = investimento2.getGuadagno();

        double totaleGuadagno = gestioneInvestimenti.aggiornaInvestimenti();
        assertEquals(1, gestioneInvestimenti.getInvestimenti().size());
        assertEquals(guadagno2, totaleGuadagno);

        totaleGuadagno = gestioneInvestimenti.aggiornaInvestimenti();
        assertEquals(0, gestioneInvestimenti.getInvestimenti().size());
        assertEquals(guadagno1, totaleGuadagno);
    }

    @Test
    void testGetInvestimenti() {
        Vector<Investimento> investimenti = gestioneInvestimenti.getInvestimenti();
        assertEquals(2, investimenti.size());
        assertTrue(investimenti.contains(investimento1));
        assertTrue(investimenti.contains(investimento2));
    }
}
