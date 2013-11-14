public class Quersumme {
	public static void main(String[] args) {
		int n;
		// == gilt nicht für string-Gleichheit, sondern Object-Gleichheit,
		// daher muss String.equals verwendet werden.
		if (args[0].equals("-i")) {
			n = Integer.parseInt(args[1]);
			System.out.println(iterierteQuersumme(n));
		} else {
			n = Integer.parseInt(args[0]);
			System.out.println(quersumme(n));
		}
	}

	public static int quersumme(int n) {
		int acc = 0;
		while (n > 9) {
			// Iteriere über Ziffern und addiere zur Zwischensumme.
			acc += n % 10;
			n = n / 10;
		}
		// Addiere verbleibende Ziffer.
		acc += n;
		return acc;
	}

	public static int iterierteQuersumme(int n) {
		while (n > 9) {
			n = quersumme(n);
		}
		return n;
	}
}
