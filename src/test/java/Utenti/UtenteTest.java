package Utenti;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class UtenteTest {
    private Utente utente;
    private String cartellaUtente;

    @BeforeEach
    void setUp() {
        utente = new Utente("testUser", "password", 500.0, 1000.0, 1);
        cartellaUtente = "DatiUtenti/testUser";
    }

    @AfterEach
    void deleteFiles() {
        File cartella = new File(cartellaUtente);
        if (cartella.exists()) {
            File[] files = cartella.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            cartella.delete();
        }
    }

    @Test
    void testNuovaSettimana() {
        utente.nuovaSettimana();
        assertEquals(2, utente.getSettimana());
        assertEquals(600.0, utente.getPortafoglio());
    }

    @Test
    void testInvestiSoldi() {
        assertTrue(utente.investiSoldi(200.0));
        assertEquals(800.0, utente.getConto());
        assertFalse(utente.investiSoldi(1200.0));
    }

    @Test
    void testDepositaGuadagno() {
        assertTrue(utente.depositaGuadagno(300.0));
        assertEquals(1300.0, utente.getConto());
        assertFalse(utente.depositaGuadagno(0.0));
    }

    @Test
    void testDeposita() {
        assertTrue(utente.deposita(200.0));
        assertEquals(1200.0, utente.getConto());
        assertEquals(300.0, utente.getPortafoglio());
        assertFalse(utente.deposita(600.0));
    }

    @Test
    void testPreleva() {
        assertTrue(utente.preleva(400.0));
        assertEquals(600.0, utente.getConto());
        assertEquals(900.0, utente.getPortafoglio());
        assertFalse(utente.preleva(700.0));
    }
}
