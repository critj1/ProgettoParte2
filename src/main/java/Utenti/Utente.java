package Utenti;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utente {
    private final String nomeUtente;
    private final String password;
    private final String fileTransazioni;
    private double conto;
    private double portafoglio;
    private int settimana;

    public Utente(String nomeUtente, String password, double portafoglio, double conto, int settimana) {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.portafoglio = portafoglio;
        this.conto = conto;
        this.settimana = settimana;
        String cartellaUtente = "DatiUtenti/" + nomeUtente;
        this.fileTransazioni = cartellaUtente + "/transazioni.txt";

        File cartella = new File(cartellaUtente);
        if (!cartella.exists()) {
            cartella.mkdirs();
        }

        File file = new File(fileTransazioni);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Errore nella creazione del file transazioni.");
            }
        }
    }

    public void registraTransazione(String tipo, double importo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTransazioni, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(timestamp + " - " + tipo + ": " + importo + "\n");
        } catch (IOException e) {
            System.out.println("Errore nella registrazione della transazione.");
        }
    }

    public String getNome() {
        return nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public int getSettimana() {
        return settimana;
    }

    public double getConto() {
        return conto;
    }

    public double getPortafoglio() {
        return portafoglio;
    }

    public void nuovaSettimana() {
        portafoglio += 100;
        settimana++;
    }

    public void investiSoldi(double importo) {
        conto -= importo;
        registraTransazione("Investimento", importo);
    }

    public boolean depositaGuadagno(double importo) {
        conto += importo;
        return importo != 0;
    }

    public boolean deposita(double importo) {
        if (importo > 0 && importo <= portafoglio) {
            conto += importo;
            portafoglio -= importo;
            return true;
        }
        return false;
    }

    public boolean preleva(double importo) {
        if (importo > 0 && importo <= conto) {
            conto -= importo;
            portafoglio += importo;
            return true;
        }
        return false;
    }
}