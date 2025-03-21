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
    private final String cartellaUtente;
    private double conto;
    private double portafoglio;
    private int settimana;

    public Utente(String nomeUtente, String password, double portafoglio, double conto, int settimana) {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.portafoglio = portafoglio;
        this.conto = conto;
        this.settimana = settimana;
        this.cartellaUtente = "DatiUtenti/" + nomeUtente;
        this.fileTransazioni = cartellaUtente + "/transazioni.csv";

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
            writer.write(timestamp + ";" + conto + ";" + portafoglio + ";" + tipo + ";" + importo + "\n");
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

    public boolean investiSoldi(double importo) {
        if (importo > conto) {
            return false;
        }
        conto -= importo;
        return true;
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

    public String getTransazioniPath() {
        return fileTransazioni;
    }

    public void eliminaUtente() {
        File cartellaElim = new File(this.cartellaUtente);
        File dataElim = new File(this.cartellaUtente + "/data.txt");
        File transazioniElim = new File(this.cartellaUtente + "/transazioni.csv");
        if (dataElim.delete() && transazioniElim.delete()) {
            cartellaElim.delete();
        }
    }
}