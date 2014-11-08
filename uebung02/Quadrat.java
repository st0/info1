public class Quadrat {
	public static int quadrat(int x) {
		int q = 0; // Zwischensumme
		int n = 0; // Zaehler
		int o = 1; // nte ungerade Zahl
		while (n < x) {
			q = q + o;
			n = n + 1;
			o = o + 2;
		}
		return q;
	}

	public static void main(String[] args) {
		int x = Math.abs(Integer.parseInt(args[0]));
		System.out.println(quadrat(x));
	}
}
