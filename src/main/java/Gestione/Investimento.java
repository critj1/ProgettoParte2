package Gestione;

public class Investimento {
    private final double soldiInvestiti;
    private final double guadagno;
    private final int percentuale;
    private final int soglia;
    private int settimane;

    public Investimento(double soldiInvestiti, int settimane, int percentuale, int soglia) {
        this.soldiInvestiti = soldiInvestiti;
        this.settimane = settimane;
        this.percentuale = percentuale;
        this.soglia = soglia;
        this.guadagno = calcolaGuadagno();
    }

    public Investimento(int settimane, double guadagno) {
        this.soldiInvestiti = 0;
        this.settimane = settimane;
        this.percentuale = 0;
        this.soglia = 0;
        this.guadagno = guadagno;

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

    public int getSettimane() {
        return settimane;
    }
}
