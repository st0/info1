// vim: et ts=4 sw=4 sts=4

public class Testen {
    public static void main( String[] args){
        int a = Integer.parseInt(args[0]);
        // Nehme den Betrag von a
        a = Math.abs(a);
        // Berechne Summe der ersten n=a ungeraden (ganzen) Zahlen
        a = sumOdd(a);


    System.out.println(a);
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

