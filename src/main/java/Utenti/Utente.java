package Utenti;

public class Utente {
    private final String nomeUtente;
    private final String password;
    private double conto;
    private double portafoglio;
    private int settimana;

    public Utente(String nomeUtente, String password, double portafoglio, double conto, int settimana) {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.portafoglio = portafoglio;
        this.conto = conto;
        this.settimana = settimana;
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
    }

    public void depositaGuadagno(double importo) {
        conto += importo;
    }


    public void deposita(double importo) {
        if (importo > 0 && importo <= portafoglio) {
            conto += importo;
            portafoglio -= importo;
        }
    }

    public void preleva(double importo) {
        if (importo > 0 && importo <= conto) {
            conto -= importo;
            portafoglio += importo;
        }
    }
}
