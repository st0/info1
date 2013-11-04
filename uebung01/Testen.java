// vim: et ts=4 sw=4 sts=4

public class Testen {
    public static void main( String[] args){
        int a = Integer.parseInt(args[0]);
        // Nehme den Betrag von a
        a = abs(a);
        // Berechne Summe der ersten n=a ungeraden (ganzen) Zahlen
        a = sumOdd(a);


    System.out.println(a);
    }

    /**
     * Betragsfunktion
     * abs(1) == abs(-1) == 1
     */
    private static int abs(int x) {
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }

    private static int sumOdd(int x) {
        int acc = 0;
        
        for (int i = 0; i < x; i++) {
            acc += (1 + 2*i);
        }
        
        return acc;
        // oder einfach x^2
    }
}

