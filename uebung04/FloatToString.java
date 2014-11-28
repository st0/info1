import java.util.List;
import java.util.Arrays;

class FloatToString {

	public static String floatToBinaryString(float f) {
		String binString = String.format("%32s", Integer.toBinaryString(
			Float.floatToRawIntBits(f))).replace(" ", "0");
		return binString;
	}

	public static void printFloat(float f, boolean gap) {
		String binString = floatToBinaryString(f);
		if (gap) {
			binString = binString.substring(0, 1) + " " + binString.substring(1, 9) +
				" " + binString.substring(9, 32);
		}
		String s = String.format("%1$14s  %2$s", f, binString);
		System.out.println(s);
	}

	public static void main(String args[]) {

		List<String> argsAsList = Arrays.asList(args);
		boolean gap = argsAsList.contains("-g");

		String sep = "------------------------------------------------";
		if (gap) sep = sep + "--";

		if (argsAsList.contains("-s")) {
			System.out.println("Sonderwerte:");
			System.out.println(sep);
			printFloat(0.0f, gap);
			printFloat(-0.0f, gap);
			printFloat(Float.POSITIVE_INFINITY, gap);
			printFloat(Float.NEGATIVE_INFINITY, gap);
			printFloat(Float.NaN, gap);
			System.out.println(sep);
		}

		if (argsAsList.contains("-e")) {
			System.out.println("Extrema:");
			System.out.println(sep);
			System.out.println("                Float.MIN_VALUE                 ");
			printFloat(Float.MIN_VALUE, gap);
			System.out.println("                Float.MAX_VALUE                 ");
			printFloat(Float.MAX_VALUE, gap);
			System.out.println("                Float.MIN_NORMAL                ");
			printFloat(Float.MIN_NORMAL, gap);
			System.out.println("                Float.MIN_EXPONENT               ");
			printFloat(Float.MIN_EXPONENT, gap);
			System.out.println("                Float.MAX_EXPONENT               ");
			printFloat(Float.MAX_EXPONENT, gap);
			System.out.println(sep);
		}


		for (int i = 0; i < args.length; i++) {
			try {
				float f = Float.parseFloat(args[i]);
				printFloat(f, gap);
			} catch (NumberFormatException e) {
				// ignorieren
			}
		}
	}
	// Bsp. einer denormalisierten Zahl: 5.877472E-39f = 00000000010000000000000000000000
	// Kleinste normalisierte Zahl ist 2^-126
}
