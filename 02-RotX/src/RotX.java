public class RotX {
 
    public static char[] minuscules = "aáàbcçdeéèfghiíìïjlmnñoóòpqrstuúùüvwxyz".toCharArray();
    public static char[] majuscules = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    
    // Utilitzem els mateixos metodes substituint el 13 per la variable desplaçament
    public static char xifraChar(char c, int desplaçament) {
        if (Character.isLetter(c)) {
            if (Character.isLowerCase(c)) {
                for (int i = 0; i < minuscules.length; i++) {
                    if (c == minuscules[i]) {
                        int posicio = (i + desplaçament) % minuscules.length;
                        if (posicio < 0) {
                            posicio = posicio + minuscules.length;
                        }
                        return minuscules[posicio];
                    }
                }
            }
            if (Character.isUpperCase(c)) {
                for (int i = 0; i < majuscules.length; i++) {
                    if (c == majuscules[i]) {
                        int posicio = (i + desplaçament) % majuscules.length;
                        if (posicio < 0) {
                            posicio = posicio + majuscules.length;
                        }
                        return majuscules[posicio];
                    }
                }
            }
        }
        return c;
    }
 
    public static char desxifraChar(char c, int desplaçament) {
        if (Character.isLetter(c)) {
            if (Character.isLowerCase(c)) {
                for (int i = 0; i < minuscules.length; i++) {
                    if (c == minuscules[i]) {
                        int posicio = (i - desplaçament) % minuscules.length;
                        if (posicio < 0) {
                            posicio = posicio + minuscules.length;
                        }
                        return minuscules[posicio];
                    }
                }
            }
            if (Character.isUpperCase(c)) {
                for (int i = 0; i < majuscules.length; i++) {
                    if (c == majuscules[i]) {
                        int posicio = (i - desplaçament) % majuscules.length;
                        if (posicio < 0) {
                            posicio = posicio + majuscules.length;
                        }
                        return majuscules[posicio];
                    }
                }
            }
        }
        return c;
    }
 
    public static String xifraRotX(String text, int desplaçament) {
        String resultat = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            resultat = resultat + xifraChar(c, desplaçament);
        }
        return resultat;
    }
 
    public static String desxifraRotX(String text, int desplaçament) {
        String resultat = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            resultat = resultat + desxifraChar(c, desplaçament);
        }
        return resultat;
    }
    
    // Fem un for al metode per a probar totes les casuistiques i fer la força bruta
    public static void forcaBrutaRotX(String cadenaXifrada) {
        System.out.println("Missatge xifrat: " + cadenaXifrada);
        System.out.println("----------------");
        for (int desplaçament = 0; desplaçament < majuscules.length; desplaçament++) {
            String desxifrat = desxifraRotX(cadenaXifrada, desplaçament);
            System.out.println("(" + desplaçament + ")-> " + desxifrat);
        }
    }
 
    public static void main(String[] args) {
        System.out.println("Xifrat");
        System.out.println("------");
        System.out.println("(0)-ABC \t\t=> " + xifraRotX("ABC", 0));
        System.out.println("(2)-XYZ \t\t=> " + xifraRotX("XYZ", 2));
        System.out.println("(4)-Hola, Mr. calçot \t=> " + xifraRotX("Hola, Mr. calçot", 4));
        System.out.println("(6)-Perdó, per tu què és? => " + xifraRotX("Perdó, per tu què és?", 6));
 
        System.out.println("\nDesxifrat");
        System.out.println("---------");
        System.out.println("(0)ABC \t\t\t=> " + desxifraRotX("ABC", 0));
        System.out.println("(2)ZAÁ \t\t\t=> " + desxifraRotX("ZAÁ", 2));
        System.out.println("(4)Ïqoc, Óú. écoèqü \t=> " + desxifraRotX("Ïqoc, Óú. écoèqü", 4));
        System.out.println("(6)Úiüht, úiü wx ùxì ív? => " + desxifraRotX("Úiüht, úiü wx ùxì ív?", 6));
 
        System.out.println();
        forcaBrutaRotX("Úiüht, úiü wx ùxì ív?");
    }
}
 
