import java.util.Arrays;

public class Fractal {
	private static String ERROR_NEGATIVE_ORDER = "Order must not be zero or negative.";
	private static String ERROR_MAX_ORDER_EXCEEDED = "Order must not be greater than 20.";

	public static void main(String[] args) {
		int order = Integer.parseInt(args[1]);

		// Check constraints.
		if (order < 1) {
			System.out.println(ERROR_NEGATIVE_ORDER);
			return;
		}
		if (order > 20) {
			System.out.println(ERROR_MAX_ORDER_EXCEEDED);
			return;
		}

		if (args[0].equals("dragon")) {
			// Output dragon curve.
			char result[] = new char[(1 << (order+1)) - 1];
			dragonIntoArray(order, result);
			String output = new String(result);
			System.out.println(result.length);
			System.out.println(output);
			return;
		}

		if (args[0].equals("levy")) {
			// Output Levy C curve.
			char result[] = new char[] {'F'};
			// Iterate based on order.
			for (int i = 1; i <= order; i++) {
				result = iterateLevyC(result);
			}
			int simplifiedLength = simplifyLevyC(result);
			System.out.println(simplifiedLength);
			System.out.println(Arrays.copyOf(result, simplifiedLength));
		}

	}


	public static void dragonIntoArray(int order, char[] array) {

		array[0] = 'F';
		int prevLength = 1;

		for (int i = 1; i <= order; i++) {
			// Length of previous (order i-1) dragon curve representation.
			// 1 << n == nth power of 2
			prevLength = ((1 << i)) - 1;

			// Insert "R" after last non-null element.
			array[prevLength] = 'R';

			// Append elements of previous curve in reverse order...
			for (int j = prevLength - 1; j >= 0; j--) {
				char c = array[j];
				// ...substituting "L" for "R" and vice versa.
				array[(2*prevLength) - j] = c == 'R' ? 'L' : c == 'L' ? 'R' : c;
			}
		}	
	}

	public static String levyC(int order) {

		String result = "F";
		for (int i = 0; i < order; i++) {
			result = "+" + result + "--" + result + "+";
		}
		result = result.replaceAll("^\\++", "").replaceAll("\\++$", "");
		result = result.replace("+--+", "");
		result = result.replace("++", "R");
		result = result.replace("--", "L");
		return result;
		
	}

	public static char[] iterateLevyC(char[] array) {
		// Create the representation of a Levy C curve of order i+1
		// given one of order i.
		char result[] = concatenateCharArrays(
			"+".toCharArray(),
			array,
			"--".toCharArray(),
			array,
			"+".toCharArray()
		);
		return result;
	}

	
	public static int simplifyLevyC(char[] array) {
		// Convert even numbers of '+'s and '-'s to Rs and Ls,
		// respectively.
		boolean skip = true;
		int offset = 0;
		int direction = 0;
		char directionChar = 'R';

		for (int i = 0; i < array.length; i++) {
			char c = array[i];
			if (c == 'F') {
				if (!skip) {
					// Insert direction instructions
					directionChar = direction < 0 ? 'L' : 'R';
					for (int j = 0; j < Math.abs(direction / 2); j++) {
						array[offset] = directionChar;
						offset++;
					}
				}
				direction = 0;
				array[offset] = 'F';
				offset++;
				skip = false;

			} else if (c == '+') {
				direction += 1;
			} else if (c == '-') {
				direction -= 1;
			}
		}
		return offset;
		
	}

	

	public static char[] concatenateCharArrays(char[]... arrays) {
		// Helper function
		// Java has no array concatenation, so we use System.arraycopy.
		int length = 0;

		// Compute length of merged array.
		for (int i = 0; i < arrays.length; i++) {
			length += arrays[i].length;
		}

		char result[] = new char[length];
		int offset = 0;

		
		for (int i = 0; i < arrays.length; i++) {
			System.arraycopy(arrays[i], 0, result, offset, arrays[i].length);
			offset += arrays[i].length;
		}

		return result;

	}
}

/*

   1 - 2F	 1 == 1 == 1 + 0
   2 - 4F    1 0 1 == 2 == 2 + 0
   3 - 8F    1 0 1 1 1 0 1 == 5 == 4 + 1
   4 - 16F   1 0 1 1 1 0 1 2 1 0 1 1 1 0 1 == 12 == 10 + 2
   5 - 32F   1 0 1 1 1 0 1 2 1 0 1 1 1 0 1 3 1 0 1 1 1 0 1 2 1 0 1 1 1 0 1 == 27 == 24 + 3

       +++++F--F+--+F--F++--++F--F+--+F--F+++--+++F--F+--+F--F++--++F--F+--+F--F++++--+
*/
