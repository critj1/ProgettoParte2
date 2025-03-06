package Gestione;

import Tools.Menu;
import Tools.ScannerInput;


public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu(new String[] {"1 --> Preleva","2 --> Deposita","3 --> Vedi conto in banca",
                "4 --> Vedi portafoglio","5 --> Investi","6 --> Avanza settimana"});
        Utente utente = new Utente(100, 0);
        GestioneInvestimenti gestioneInvestimenti = new GestioneInvestimenti();
        int settimana = 1;
        int scelta;
        do {
            System.out.println("Settimana " + settimana);
            scelta = menu.scelta();
            switch (scelta) {
                case 1: {
                    System.out.print("Quanto vuoi prelevare? ");
                    double prelievo = ScannerInput.getDouble();
                    utente.preleva(prelievo);
                    break;
                }
                case 2: {
                    System.out.print("Quanto vuoi depositare? ");
                    double deposito = ScannerInput.getDouble();
                    utente.deposita(deposito);
                    break;
                }
                case 3: {
                    System.out.println("Saldo in banca: " + utente.getConto());
                    break;
                }
                case 4: {
                    System.out.println("Saldo portafoglio: " + utente.getPortafoglio());
                    break;
                }
                case 5: {
                    if (utente.getConto() <= 0) {
                        System.out.println("Non hai abbastanza soldi per investire.");
                        break;
                    }
                    System.out.print("Inserisci l'importo da investire: ");
                    double importo = ScannerInput.getDouble();

                    while (importo > utente.getConto()) {
                        System.out.println("Saldo insufficente riprova");
                        importo = ScannerInput.getDouble();
                    }
                    utente.investiSoldi(importo);

                    
                    System.out.println("1 --> investimento di breve durata");
                    System.out.println("2 --> investimento di media durata ");
                    System.out.println("3 --> investimento di lunga durata ");

                    int settimane = switch (sceltaMenuSecondario()) {
                        case 1 -> 1;
                        case 2 -> 2;
                        case 3 -> 3;
                        default -> 0;
                    };

                    System.out.println("1 --> investimento a basso rischio e con basso guadagno ");
                    System.out.println("2 --> investimento a medio rischio e con medio guadagno  ");
                    System.out.println("3 --> investimento a alto rischio e con alto guadagno ");

                    int percentuale = 0;
                    int soglia = 0;

                    switch (sceltaMenuSecondario()) {
                        case 1: {
                            percentuale = (int) (Math.random() * 10);
                            soglia = 80;
                            break;
                        }
                        case 2: {
                            percentuale = (int) ((Math.random() * 40) + 10);
                            soglia = 50;
                            break;
                        }
                        case 3: {
                            percentuale = (int) ((Math.random() * 50) + 50);
                            soglia = 30;
                            break;
                        }
                    }
                    gestioneInvestimenti.aggiungiInvestimento(new Investimento(importo, settimane, percentuale, soglia));
                    break;
                }
                case 6: {
                    settimana++;
                    double guadagno = gestioneInvestimenti.aggiornaInvestimenti();
                    utente.depositaGuadagno(guadagno);
                    utente.nuovaSettimana();
                    break;
                }
                case 0: {
                    System.out.println("Arrivederci!");
                    break;
                }
            }
        }while (scelta != 0);
    }

    public static int sceltaMenuSecondario(){
        System.out.print("\nSCEGLI --> ");
        int scelta = ScannerInput.getIntero();
        while (scelta > 3 || scelta < 1) {
            System.out.println("\nERRORE! ");
            System.out.print("SCEGLI --> ");
            scelta = ScannerInput.getIntero();
        }
        return scelta;
    }
}
