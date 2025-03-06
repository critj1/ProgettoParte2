package Tools;
import java.util.Scanner;

public class ScannerInput {

    /**
     * metodo per ricevere un intero da tastiera con uno scanner e controllo con try
     * catch
     *
     * @return restituisce un numero intero
     */
    public static int getIntero() {
        Scanner sc = new Scanner(System.in);

        int intero = 0;
        boolean ok;

        do {

            ok = true;
            try {

                String input = sc.nextLine().trim();
                intero = Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.print("\nErrore inserisci un numero valido.  --> ");
                ok = false;
            }
        } while (!ok);

        return intero;
    }

    /**
     * metodo per ricevere un double da tastiera con uno scanner e controllo con try
     * catch
     *
     * @return restituisce un numero double
     */
	public static double getDouble() {
		Scanner sc = new Scanner(System.in);

		double doubleNum = 0;
		boolean ok;

		do {

			ok = true;
			try {

				String input = sc.nextLine().trim();
				doubleNum = Double.parseDouble(input);

			} catch (NumberFormatException e) {
				System.out.print("\nErrore inserisci un numero valido.  --> ");
				ok = false;
			}
		} while (!ok);

		return doubleNum;
	}



}

