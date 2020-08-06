public class Main {
    String myString = "3+(7*6)*((2+3)*(6+7)*2";
    public static void main(String[] args) {
        System.out.println("Test");
        berechnen("6*7*2*2*3*4+1/56-1",1);
    }


    public static String bearbeiten(String string, int index_of_zeichen,char  operation){
        int poz_stern= index_of_zeichen;

        if( poz_stern >0){
            int  poz_zahl_before = -1;
            int index_in_string;
            // Es gibt ein stern, wir suchen zahlen before and after
            for (int i=poz_stern-1; i>=0 ; i--){
                if ( (string.substring(i,i+1).equals("*")) || (string.substring(i,i+1).equals("/")) || (string.substring(i,i+1).equals("+"))   ||  (string.substring(i,i+1).equals("%"))  || (string.substring(i,i+1).equals("-")) )
                {
                    poz_zahl_before = i+1;
                    break;
                }
            }
            if (poz_zahl_before == -1) poz_zahl_before = 0;

            // jetzt haben wir index anfang

            int poz_zahl_after =-1 ;

            for (int i=poz_stern+1; i<=string.length()-1 ; i++){
                if (    (string.substring(i,i+1).equals("*")) ||
                        (string.substring(i,i+1).equals("/")) ||
                        (string.substring(i,i+1).equals("+")) ||
                        (string.substring(i,i+1).equals("%")) ||
                        (string.substring(i,i+1).equals("-"))        )
                {
                    poz_zahl_after = i-1;
                    break;
                }
            } //endfor
            if (    poz_zahl_after == -1)     poz_zahl_after= string.length()-1 ;
            // jetzt haben wir index ende

            double zahl1, zahl2, ergebnis;

            zahl1= Double.parseDouble(string.substring(poz_zahl_before, poz_stern ));
            zahl2= Double.parseDouble( string.substring(poz_stern+1, poz_zahl_after+1 ) );

            switch (operation){
                case '*' -> {ergebnis =zahl1 * zahl2; }
                case '/' -> {ergebnis =zahl1 / zahl2; }
                case '+' -> {ergebnis =zahl1 + zahl2; }
                case '-' -> {ergebnis =zahl1 - zahl2; }
                default ->  {ergebnis = -1000000000; }
            }
            System.out.println(ergebnis);

            // string neu initialisieren

            string = string.substring(0,poz_zahl_before) + ergebnis + string.substring(poz_zahl_after+1,string.length());

        }
        return string;
    }


    // =============================================================================================================

    public static double berechnen(String string, int iteration){

            System.out.println("String is:"+string + "Itaration:"+iteration);

            while (string.indexOf('*')>-1){
                string= bearbeiten(string,string.indexOf('*'),'*');
                berechnen(string,iteration+1);
            }

            while (string.indexOf('/')>-1){
                string= bearbeiten(string,string.indexOf('/'),'/');
                berechnen(string,iteration+1);
            }

        while (string.indexOf('+')>-1){
            string= bearbeiten(string,string.indexOf('+'),'+');
            berechnen(string,iteration+1);
        }

        while (string.indexOf('-')>-1){
            string= bearbeiten(string,string.indexOf('-'),'-');
            berechnen(string,iteration+1);
        }

    return Double.parseDouble(string);

    }



}
