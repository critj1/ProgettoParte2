package Gestione;

public class Investimento {
    private final double soldiInvestiti;
    private final double guadagno;
    private int settimane;
    private final int percentuale;
    private final int soglia;

    public Investimento(double soldiInvestiti, int settimane, int percentuale, int soglia) {
        this.soldiInvestiti = soldiInvestiti;
        this.settimane = settimane;
        this.percentuale = percentuale;
        this.soglia = soglia;
        this.guadagno = calcolaGuadagno();
    }

    private double calcolaGuadagno() {
        int n = (int) (Math.random() * 100);
        return (n < soglia) ? soldiInvestiti * (1 + (percentuale / 100.0)) : soldiInvestiti * (1 - (percentuale / 100.0));
    }

    public boolean avanzaSettimana() {
        settimane--;
        return settimane <= 0;
    }

    public double getGuadagno() {
        return guadagno;
    }
}
