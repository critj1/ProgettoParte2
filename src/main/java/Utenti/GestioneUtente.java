package Utenti;

import Gestione.GestioneInvestimenti;
import Gestione.Investimento;
import Tools.ScannerInput;

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

    private static int sceltaInvestimento() {
        System.out.print("\nSCEGLI --> ");
        int scelta = ScannerInput.getIntero();
        while (scelta > 3 || scelta < 1) {
            System.out.println("\nERRORE! ");
            System.out.print("SCEGLI --> ");
            scelta = ScannerInput.getIntero();
        }
        return scelta;
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

    public void gestisciInvestimenti(Utente utente) {
        if (utente.getConto() <= 0) {
            System.out.println("Non hai abbastanza soldi nel conto");
            return;
        }

        System.out.print("Inserisci l'importo da investire: ");
        double importo = ScannerInput.getDouble();
        while (importo > utente.getConto()) {
            System.out.print("Saldo insufficiente, riprova : ");
            importo = ScannerInput.getDouble();
        }
        utente.investiSoldi(importo);

        System.out.println("1 --> investimento di breve durata");
        System.out.println("2 --> investimento di media durata");
        System.out.println("3 --> investimento di lunga durata");
        int settimane = sceltaInvestimento();

        System.out.println("1 --> investimento a basso rischio");
        System.out.println("2 --> investimento a medio rischio");
        System.out.println("3 --> investimento a alto rischio");
        int rischio = sceltaInvestimento();

        int percentuale = 0, soglia = 0;
        switch (rischio) {
            case 1 -> {
                percentuale = (int) (Math.random() * 10);
                soglia = 80;
            }
            case 2 -> {
                percentuale = (int) ((Math.random() * 40) + 10);
                soglia = 50;
            }
            case 3 -> {
                percentuale = (int) ((Math.random() * 50) + 50);
                soglia = 30;
            }
        }

        gestioneInvestimenti.aggiungiInvestimento(new Investimento(importo, settimane, percentuale, soglia));
    }
}