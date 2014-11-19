public class Bauernmultiplikation {
	public static void main(String args[]) {

		int result = 1; // leeres Produkt
		int a = 1;

		for (int i = 0; i < args.length; i++) {
			try {
				a = Integer.parseInt(args[i]);
			} catch (NumberFormatException e) {
				System.err.println("Usage: java Bauernmultiplikation [int [int] ...]");
				System.exit(1);
			}
			result = bauernmultiplikation(result, a);
		}
		System.out.println(result);
	}

	public static int bauernmultiplikation(int a, int b) {
		// -------------
		// Initialisiere
		// -------------
		int p = 0;
		// Ist das Vorzeichen des Produkts von a und b negativ?
		boolean v = (a < 0 && b >= 0) || (b < 0 && a >= 0);

		// --------
		// Iteriere
		// --------
		while (b != 0) {
			// Falls b ungerade
			if (b % 2 != 0) {
				p += a;
			}
			a += a;
			b /= 2;
		}

		// -----------
		// Finalisiere
		// -----------
		// Falls noetig, veraendere das Vorzeichen von p
		if (p < 0 != v) {
			p = -p;
		}
		return p;
	}
}
