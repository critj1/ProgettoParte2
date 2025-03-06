package Gestione;

import java.util.Vector;

public class GestioneInvestimenti {
    private final Vector<Investimento> investimenti;

    public GestioneInvestimenti(){
        this.investimenti = new Vector<>(10,5);
    }

    public void aggiungiInvestimento(Investimento investimento) {
        investimenti.add(investimento);
    }

    /**
     * il metodo usa il removeIf per iterare il vector investimenti e rimuoverli se completati tramite
     * l'uso di un lambda che restitusice se l'investimento e completato aggiungendo il guadagno dentro un
     * array totaleGuadagno per permettere la modifica nel lambda.
     *
     * @return ritorna il guadagno complessivo di ogni investimento completato (puo essere negativo)
     */
    public double aggiornaInvestimenti() {
        double[] totaleGuadagno = {0};

        investimenti.removeIf(i -> {
            if (i.avanzaSettimana()) {
                totaleGuadagno[0] += i.getGuadagno();
                return true;
            }
            return false;
        });

        return totaleGuadagno[0];
    }
}
