package Gestione;

import Tools.Menu;
import Tools.ScannerInput;
import Utenti.GestioneUtente;
import Utenti.Utente;


public class Main {
    public static void main(String[] args) {
        GestioneUtente gestioneUtente = new GestioneUtente();
        Utente utente = gestioneUtente.caricaUtente();
        GestioneInvestimenti gestioneInvestimenti = gestioneUtente.getGestioneInvestimenti();

        Menu menu = new Menu(new String[]{"1 --> Preleva", "2 --> Deposita", "3 --> Vedi conto in banca",
                "4 --> Vedi portafoglio", "5 --> Investi", "6 --> Avanza settimana"});
        int settimana = utente.getSettimana();
        int scelta;
        do {
            System.out.println("Settimana " + settimana);
            scelta = menu.scelta();
            switch (scelta) {
                case 1 -> {
                    System.out.print("Quanto vuoi prelevare? ");
                    utente.preleva(ScannerInput.getDouble());
                }
                case 2 -> {
                    System.out.print("Quanto vuoi depositare? ");
                    utente.deposita(ScannerInput.getDouble());
                }
                case 3 -> System.out.println("Saldo in banca: " + utente.getConto());
                case 4 -> System.out.println("Saldo portafoglio: " + utente.getPortafoglio());
                case 5 -> gestioneUtente.gestisciInvestimenti(utente);
                case 6 -> {
                    settimana++;
                    utente.depositaGuadagno(gestioneInvestimenti.aggiornaInvestimenti());
                    utente.nuovaSettimana();
                }
                case 0 -> {
                    gestioneUtente.salvaDatiUtente(utente);
                    System.out.println("Arrivederci!");
                }
            }
        } while (scelta != 0);
    }
}