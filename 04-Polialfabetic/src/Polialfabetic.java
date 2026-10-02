import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

    private static final int clauSecreta = 1834692;
    public static Random random;
    public static char[] majuscules = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    public static char[] alfabetPermutat;

    public static void initRandom(int clau) {
        random = new Random(clau);
    }

    public static void permutaAlfabet() {

        List<Character> llista = new ArrayList<>();
        for (char c : majuscules) {
            llista.add(c);
        }

        Collections.shuffle(llista, random);

        alfabetPermutat = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
            alfabetPermutat[i] = llista.get(i);
        }
    }

    private static int indexDe(char[] alfabet, char c) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == c) return i;
        }
        return -1;
    }

    public static String xifraPoliAlfa(String msg) {
        
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < msg.length(); i++) {
            permutaAlfabet();
            char lletra = msg.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletra);
            int pos = indexDe(majuscules, Character.toUpperCase(lletra));

            if (pos == -1) {
                sb.append(lletra);
            } else {
                char xifrada = alfabetPermutat[pos];
                if (esMinuscula) {
                    sb.append(Character.toLowerCase(xifrada));
                } else {
                    sb.append(xifrada);
                }
            }
        }
        return sb.toString();
    }

    public static String desxifraPoliAlfa(String msgXifrat) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < msgXifrat.length(); i++) {
            permutaAlfabet();
            char lletra = msgXifrat.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletra);
            int pos = indexDe(alfabetPermutat, Character.toUpperCase(lletra));

            if (pos == -1) {
                sb.append(lletra);
            } else {
                char original = majuscules[pos];
                if (esMinuscula) {
                    sb.append(Character.toLowerCase(original));
                } else {
                    sb.append(original);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre",
                        "Test 02 Taüll, DÍA, año",
                        "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}
