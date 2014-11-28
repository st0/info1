import java.util.Arrays;

class StringToFloat {

	private static void badInput(String str, String msg, int i) {
		System.err.println("Fehlerhafte Eingabe: " + msg);
		System.err.println(str);
		System.err.println(String.format("%1$" + (i+1) + "s", "^"));
		System.err.println("Erwarte: 32-Zeichenkette aus '0' und '1'");
		System.exit(1);
	}

	private static boolean bitsAreZero(int[] offsets, int[] bits) {
		for (int i = 0; i < offsets.length; i++) {
			if (bits[offsets[i]] != 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String args[]) {
		String str = "";
		// Verbinde Argumente zu Einem
		for (int i = 0; i < args.length; i++) {
			str = str.concat(args[i]);
		}

		String output = "";
		int[] digits = new int[32];

		// Wandle Zeichenkette um in Int-Array
		for (int i = 0; i < Math.min(str.length(), 32); i++) {
			char c = str.charAt(i);
			if (c == '0') {
				digits[i] = 0;
			} else if (c == '1') {
				digits[i] = 1;
			} else {
				badInput(str, "Zeichen '" + c + "' ist nicht erlaubt", i);
			}
		}

		// Ueberpruefe Laenge der Zeichenkette
		if (str.length() > 32) {
			badInput(str, "zu lang", 32);
		} else if (str.length() < 32) {
			badInput(str, "zu kurz", Math.max(str.length()-1, 0));
		}

		// Merke: from -- inclusive / to -- exclusive
		int[] exponentBits = Arrays.copyOfRange(digits, 1, 9);
		int[] mantissaBits = Arrays.copyOfRange(digits, 9, 32);

		/*
		 * VORZEICHEN
		 * 1 10000001 01001000000000000000000
		 * ^
		 * 1 = -1  0 = 1
		 */
		int sign = (digits[0] == 1) ? -1 : 1;

		/*
		 * EXPONENT
		 * 1 10000001 01001000000000000000000
		 *   ^^^^^^^^
		 */
		int exp = 0;

		for (int i = 0; i < 8; i++) {
			exp += exponentBits[i] * Math.pow(2, 7-i);
		}

		/*
		 * MANTISSE
		 * 1 10000001 01001000000000000000000
		 *            ^^^^^^^^^^^^^^^^^^^^^^^
		 */
		int mant = 0;

		for (int i = 0; i < 23; i++) {
			mant += mantissaBits[i] * Math.pow(2, 22-i);
		}

		float f = 0;

		// Siehe ieee754.pdf S. 8: "3.2.1 Single"
		if (exp == 255 && mant != 0) {
			output = "NaN";
		} else if (exp == 255 && mant == 0) {
			if (sign == 1) {
				output = "POSITIVE_INFINITY";
			} else {
				output = "NEGATIVE_INFINITY";
			}
		} else if (exp > 0 && exp < 255) {
			// Normalisiert
			// ------------
			// Rechenweg:
			//    gesucht ist ein int m mit:
			//    m * 2^-23 = (mant * 2^-23) + 1
			// => m         = (mant * 2^-23) * 2^23 + 2^23
			// => m         = mant + 2^23
			int m = mant + (int) Math.pow(2, 23);
			f = sign * m * (float) Math.pow(2, exp-150);
			System.out.println("s:\t\t" + sign);
			System.out.println("m:\t\t" + m);
			System.out.println("e:\t\t" + exp);
			System.out.println("m*2^-23:\t" + (m * (float) Math.pow(2, -23)));
			output = "s*m*2^(e-150):\t" + f;
		} else if (exp == 0 && mant != 0) {
			// Denormalisiert
			// --------------
			// Rechenweg:
			//    f = sign * (float) Math.pow(2, -126) * mant * (float) Math.pow(2, -23);
			// => f = sign * (float) Math.pow(2, -149) * mant
			// => f = sign * (float) Math.pow(2, 1-150) * mant
			// => e = 1
			int e = 1;
			int m = mant;
			f = sign * m * (float) Math.pow(2, e-150);
			System.out.println("s:\t\t" + sign);
			System.out.println("m:\t\t" + m);
			System.out.println("e:\t\t" + e);
			System.out.println("m*2^-23:\t" + (m * (float) Math.pow(2, -23)));
			output = "s*m*2^(e-150):\t" + f;
		} else {
			if (sign == 1) {
				output = "+0";
			} else {
				output = "-0";
			}
		}

		// Ausgabe
		System.out.println(output);
	}
}
