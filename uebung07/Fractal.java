public class Fractal {
	private static String ERROR_NEGATIVE_ORDER = "Order must not be negative.";
	private static String ERROR_MAX_ORDER_EXCEEDED = "Order must not be greater than 20.";

	public static void main(String[] args) {
		String output = "";
		int order = Integer.parseInt(args[0]);

		if (order < 1) {
			System.out.println(ERROR_NEGATIVE_ORDER);
			return;
		}
		if (order > 20) {
			System.out.println(ERROR_MAX_ORDER_EXCEEDED);
			return;
		}

		String result = levyC(order);
		System.out.println(result.length());
		System.out.println(result);
	}

	public static String dragon(int order) {

		String result = "F";
		for (int i = 0; i < order; i++) {
			result = result + "R" + new StringBuilder(
				result.replace("L", "0")
				.replace("R", "L")
				.replace("0", "R")).reverse().toString();
		}	
		return result;
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
}
