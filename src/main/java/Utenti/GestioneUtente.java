package Utenti;

import Gestione.GestioneInvestimenti;
import Gestione.Investimento;

import java.io.*;


public class GestioneUtente {
    private static final String CARTELLA_DATI = "DatiUtenti";
    private final GestioneInvestimenti gestioneInvestimenti;
    private Utente utente;

    public GestioneUtente() {
        gestioneInvestimenti = new GestioneInvestimenti();
        File directory = new File(CARTELLA_DATI);
        if (directory.mkdir()) {
            System.out.println("Creata cartella DatiUtenti");
        }

    }

    public GestioneInvestimenti getGestioneInvestimenti() {
        return gestioneInvestimenti;
    }

    public Utente getUtente() {
        return utente;
    }

    public boolean caricaUtente(String username, String password) {
        String cartellaUtente = CARTELLA_DATI + "/" + username;
        File fileUtente = new File(cartellaUtente + "/data.txt");

        if (fileUtente.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileUtente))) {
                String passwordSalvata = br.readLine();
                if (!password.equals(passwordSalvata)) {
                    return false;
                }
                double portafoglio = Double.parseDouble(br.readLine());
                double conto = Double.parseDouble(br.readLine());
                int settimana = Integer.parseInt(br.readLine());
                this.utente = new Utente(username, passwordSalvata, portafoglio, conto, settimana);

                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] dati = linea.split(",");
                    int settimane = Integer.parseInt(dati[0]);
                    double guadagno = Double.parseDouble(dati[1]);
                    gestioneInvestimenti.aggiungiInvestimento(new Investimento(settimane, guadagno));
                }
                return true;
            } catch (IOException e) {
                System.out.println("Errore nella lettura del file utente.");
            }
        }
        return false;
    }

    public boolean registraUtente(String username, String password) {
        String cartellaUtente = CARTELLA_DATI + "/" + username;
        File fileUtente = new File(cartellaUtente + "/data.txt");

        if (!fileUtente.exists()) {
            this.utente = new Utente(username, password, 100, 0, 1);
            salvaDatiUtente(this.utente,this.gestioneInvestimenti);
            return true;
        }
        return false;

    }


    public void salvaDatiUtente(Utente utente,GestioneInvestimenti gestioneInvestimenti) {
        String cartellaUtente = CARTELLA_DATI + "/" + utente.getNome();
        File cartella = new File(cartellaUtente);
        if (!cartella.exists()) {
            cartella.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cartellaUtente + "/data.txt"))) {
            bw.write(utente.getPassword() + "\n");
            bw.write(utente.getPortafoglio() + "\n");
            bw.write(utente.getConto() + "\n");
            bw.write(utente.getSettimana() + "\n");

            for (Investimento investimento : gestioneInvestimenti.getInvestimenti()) {
                bw.write(investimento.getSettimane() + "," + investimento.getGuadagno() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file utente.");
        }
    }

}