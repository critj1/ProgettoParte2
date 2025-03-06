package Gestione;

public class Utente {
    private double conto;
    private double portafoglio;

    public Utente(double portafoglio, double conto) {
        this.portafoglio = portafoglio;
        this.conto = conto;
    }

    public double getConto() {
        return conto;
    }

    public double getPortafoglio() {
        return portafoglio;
    }

    public void nuovaSettimana(){
        portafoglio += 100;
    }
    public void investiSoldi(double importo){
    conto -= importo;
    }
    public void depositaGuadagno(double importo){
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
