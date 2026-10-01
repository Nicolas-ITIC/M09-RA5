import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

    public static char[] majuscules = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    public static char[] permutat = permutaAlfabet(majuscules);

    public static char[] permutaAlfabet(char[] alfabet) {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }
 
        Collections.shuffle(llista);
 
        char[] resultat = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
            resultat[i] = llista.get(i);
        }
        return resultat;
    }
 
    public static String xifraMonoAlfa(String cadena) {
        char[] original = cadena.toCharArray();
        char[] sortida = new char[original.length];
 
        for (int i = 0; i < original.length; i++) {
            char c = original[i];
            char majuscula = Character.toUpperCase(c);
            int index = indexOf(majuscules, majuscula);
 
            if (index == -1) {
                sortida[i] = c;
            } else {
                char xifrat = permutat[index];
                if (Character.isLowerCase(c)) {
                    xifrat = Character.toLowerCase(xifrat);
                }
                sortida[i] = xifrat;
            }
        }
 
        return new String(sortida);
    }
 
    public static String desxifraMonoAlfa(String cadena) {
        char[] xifrada = cadena.toCharArray();
        char[] sortida = new char[xifrada.length];
 
        for (int i = 0; i < xifrada.length; i++) {
            char c = xifrada[i];
            char majuscula = Character.toUpperCase(c);
            int index = indexOf(permutat, majuscula);
 
            if (index == -1) {
                sortida[i] = c;
            } 
            else {
                char original = majuscules[index];
                if (Character.isLowerCase(c)) {
                    original = Character.toLowerCase(original);
                }
                sortida[i] = original;
            }
        }
 
        return new String(sortida);
    }

 
    private static int indexOf(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }
 
    public static void main(String[] args) {
        System.out.println("Alfabet original: " + new String(majuscules));
        System.out.println("Alfabet permutat: " + new String(permutat));
        System.out.println();
 
        String[] tests = {
                "àrbitre, coixi, Perímetre",
                "Taüll, DÍA, año",
                "Peça, Òrrius, Bòvila"
        };
 
        System.out.println("Xifratge:");
        String[] xifrats = new String[tests.length];
        for (int i = 0; i < tests.length; i++) {
            xifrats[i] = xifraMonoAlfa(tests[i]);
            System.out.println("Test " + i + " " + tests[i]+ " -> " + xifrats[i]);
        }
 
        System.out.println();
        System.out.println("Desxifratge:");
        for (int i = 0; i < xifrats.length; i++) {
            String desxifrat = desxifraMonoAlfa(xifrats[i]);
            System.out.println("Test " + i + " " + xifrats[i] + " -> " + desxifrat);
        }
    }
}