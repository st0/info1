public class badisch {
    public static void main( String[] args){
		String s = "";
		int z = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		hornerUmkehrung(z, b);
		hornerUmkehrungRekursiv(z, b);
    }

	public static void hornerUmkehrung(int z, int b) {
		String s = "";
		do {
			s = (z % b) + s;
			z = z / b;
		} while (z != 0);
		System.out.println(s);
	}

	public static void hornerUmkehrungRekursiv(int z, int b) {
		if (z != 0) {
			System.out.print("hornerUmkehrungRekursiv(");
			System.out.print(z);
			System.out.print(", ");
			System.out.print(b);
			System.out.print(")\t\t");
			System.out.print(z % b);
			System.out.print("\n");
			//System.out.print(z % b);
			hornerUmkehrungRekursiv(z / b, b);
		}
	}
}
