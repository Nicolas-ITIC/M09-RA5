import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

    public static char[] majuscules = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    public static char[] permutat = permutaAlfabet(majuscules);
    

    private static int indexOf(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }

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

    }

}
 
