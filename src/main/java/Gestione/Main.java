package Gestione;

import GUI.LoginFrame;
import GUI.MainFrame;
import Tools.Menu;
import Tools.ScannerInput;
import Utenti.GestioneUtente;
import Utenti.Utente;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginFrame loginFrame = new LoginFrame();

        // Aggiunge un listener per rilevare la chiusura della finestra di login
        loginFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Quando il login si chiude, apriamo il MainFrame
                SwingUtilities.invokeLater(() -> new MainFrame(loginFrame.getGestioneUtente()));
            }
        });

//        GestioneUtente gestioneUtente = new GestioneUtente();
//        gestioneUtente.caricaUtente("ciao","nui");
//        Utente utente = gestioneUtente.getUtente();
//        GestioneInvestimenti gestioneInvestimenti = gestioneUtente.getGestioneInvestimenti();
//
//        Menu menu = new Menu(new String[]{"Preleva", "Deposita", "Vedi conto in banca", "Vedi portafoglio", "Investi", "Avanza settimana"});
//        int settimana = utente.getSettimana();
//        int scelta;
//        do {
//            System.out.println("Settimana " + settimana);
//            scelta = menu.scelta();
//            switch (scelta) {
//                case 1 -> {
//                    System.out.print("Quanto vuoi prelevare? ");
//                    utente.preleva(ScannerInput.getDouble());
//                }
//                case 2 -> {
//                    System.out.print("Quanto vuoi depositare? ");
//                    utente.deposita(ScannerInput.getDouble());
//                }
//                case 3 -> System.out.println("Saldo in banca: " + utente.getConto());
//                case 4 -> System.out.println("Saldo portafoglio: " + utente.getPortafoglio());
//                case 5 -> gestioneUtente.gestisciInvestimenti(utente);
//                case 6 -> {
//                    settimana++;
//                    utente.depositaGuadagno(gestioneInvestimenti.aggiornaInvestimenti());
//                    utente.nuovaSettimana();
//                }
//                case 0 -> {
//                    gestioneUtente.salvaDatiUtente(utente);
//                    System.out.println("Arrivederci!");
//                }
//            }
//        } while (scelta != 0);
        }
}