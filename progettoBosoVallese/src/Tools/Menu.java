package Tools;

public class Menu {
    private final String[] opzioni;

    public Menu(String[] opzioni) {
        this.opzioni = opzioni;
    }

    /**
     * Metodo che stampa il menu usando l'array di opzioni.
     */
    private void stampa() {
        for (int i = 0; i < opzioni.length; i++) {
            System.out.println((i + 1) + ") " + opzioni[i]);
        }
        System.out.println("\n0. ESCI");
    }

    /**
     * metodo per ricevere da tastiera la scelta per il menu
     *
     * @return la scelta del utente
     */
    public int scelta() {
        stampa();
        System.out.print("\nSCEGLI --> ");
        int scelta = ScannerInput.getIntero();

        while (scelta > opzioni.length || scelta < 0) {
            System.out.println("\nERRORE! ");
            System.out.print("SCEGLI --> ");
            scelta = ScannerInput.getIntero();
        }
        return scelta;
    }
}



