package Tools;
import java.util.Scanner;

public class ScannerInput {

    /**
     * metodo per ricevere un intero positivo da tastiera con uno scanner e controllo con try
     * catch
     *
     * @return restituisce un numero intero
     */
    public static int getIntero() {
        Scanner scanner = new Scanner(System.in);

        int intero = 0;
        boolean ok;

        do {

            ok = true;
            try {
                String input = scanner.nextLine().trim();
                intero = Integer.parseInt(input);

                if (intero < 0) {
                    System.out.print("\nErrore: inserisci un numero maggiore o uguale a zero. --> ");
                    ok = false;
                }
            } catch (NumberFormatException e) {
                System.out.print("\nErrore inserisci un numero valido.  --> ");
                ok = false;
            }
        } while (!ok);

        return intero;
    }
    /**
     * metodo per ricevere un double positivo da tastiera con uno scanner e controllo con try
     * catch
     *
     * @return restituisce un numero double
     */
	public static double getDouble() {
		Scanner scanner = new Scanner(System.in);

		double doubleNum = 0;
		boolean ok;

		do {

			ok = true;
			try {
                String input = scanner.nextLine().trim();
                doubleNum = Double.parseDouble(input);

                if (doubleNum < 0) {
                    System.out.print("\nErrore: inserisci un numero valido.  --> ");
                    ok = false;
                }
			} catch (NumberFormatException e) {
				System.out.print("\nErrore inserisci un numero valido.  --> ");
				ok = false;
			}
		} while (!ok);

		return doubleNum;
	}
}

