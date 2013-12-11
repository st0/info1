class Foo {
	public static String ba(int n) {
		String s = "";
		int p;
		for (p=2; p*p <= n; p++) {
			if (n%p == 0) {
				s = p + "*" + ba(n/p);
				break; // verlassen der for - Schleife
			}
		}

		if (p*p> n)
			s = s + n;
		return s;
	}

	public static void baRekursiv(int n, int p) {

		if (n%p == 0) {
			System.out.print("*");
			System.out.print(p);
		} else {
			p = p + 1;
		}

		baRekursiv(n/p, p);
	}

	public static void main(String[] args) {

		int n = Integer.parseInt(args[0]);
		System.out.println(ba(n));
		baRekursiv(n, 2);
	}

}
