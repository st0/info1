/* Time-stamp: <2013-08-13 12:44:06 damm>
   Quelltextsammlung fuer Informatik I */



public class GGTKlassisch {

	public static void
	printStep(String fmt, int x, int y) {
		String code = String.format(fmt, x, y);
		System.out.println(String.format("| %d\t| %d\t| %s",
			x, y, code));
	}

    public static int 
	euklidKlassisch(int x, int y){// Wertuebernahme
		String pre = "";
		System.out.println("| x\t| y\t| Code");
			printStep("euklidKlassisch(%d, %d)", x, y);
		while ( y != 0) {// y ungleich 0?
			printStep("  while(%d != 0)", x, y);
			printStep("    if(%d > %d)", x, y);
			if ( x > y) {
				pre = String.format("      x = %d - %d", x, y);
				x = x - y;
				printStep(pre, x, y);
			} else {
				pre = String.format("      y = %d - %d", y, x);
				y = y - x;
				printStep(pre, x, y);
			}
		}
		printStep("while(0 != 0)", x, y);
		printStep("return %d", x, y);
		return x; // Wertauslieferung
		}    

	public static void
	printStep3(String fmt, int x, int y, Object r) {
		String code = String.format(fmt, x, y, r);
		System.out.println(String.format("| %d\t| %d\t| %s\t| %s",
			x, y, r, code));
	}
    
    public static int 
	euklidModern(int x, int y) {
		int r;
		// Umgehe error: variable r might not have been initialized
		r = 0;
		boolean firstIteration = true;
		System.out.println("| x\t| y\t| r\t| Code");
		printStep3("euklidModern(%d, %d)", x, y, "-");
		while ( y != 0) { 
			if (firstIteration) {
				firstIteration = false;
				printStep3("  while(%2$d != 0)", x, y, "-");
			} else {
				printStep3("  while(%2$d != 0)", x, y, r);
			}
			r = x % y; 
			printStep3("    r = %d %% %d", x, y, r);
			x = y; 
			printStep3("    x = %2$d", x, y, r);
			y = r;
			printStep3("    y = %3$d", x, y, r);
		}
		printStep3("return %d", x, y, "-");
		return x; // Wertauslieferung
    }    
    
    public static int euklidRekursiv (int x, int y) {
	if ( y == 0) 
	    return x;
	return euklidRekursiv ( y, x % y);
    }
    
    public static void main( String[] args){ 
	int a = Integer.parseInt(args[0]); // Wertuebernahme von ..
	int b = Integer.parseInt(args[1]); // .. Kommandozeile
	if ( a <= 0 || b <= 0) // '||' steht fuer 'oder'
	    System.out.println("nur positive Argumente!!");
	int d;
	d = euklidKlassisch(a,b); // Methodenaufruf
	//d = euklidModern(a, b);
	//System.out.println(d); // Bildschirmausgabe
    } 
}
