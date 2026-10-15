package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    private Random random;
    public static final char[] MAJUSCULES = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private char[] alfabetPermutat;

    public void initRandom(long clau) {
        random = new Random(clau);
    }

    public void permutaAlfabet() {
        List<Character> llista = new ArrayList<>();
        for (char c : MAJUSCULES) {
            llista.add(c);
        }
        Collections.shuffle(llista, random);
        alfabetPermutat = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
            alfabetPermutat[i] = llista.get(i);
        }
    }

    private int indexDe(char[] alfabet, char c) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == c) return i;
        }
        return -1;
    }

    public String xifraPoliAlfa(String msg) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            permutaAlfabet();
            char lletra = msg.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletra);
            int pos = indexDe(MAJUSCULES, Character.toUpperCase(lletra));
            if (pos == -1) {
                sb.append(lletra);
            } else {
                char xifrada = alfabetPermutat[pos];
                sb.append(esMinuscula ? Character.toLowerCase(xifrada) : xifrada);
            }
        }
        return sb.toString();
    }

    public String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msgXifrat.length(); i++) {
            permutaAlfabet();
            char lletra = msgXifrat.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletra);
            int pos = indexDe(alfabetPermutat, Character.toUpperCase(lletra));
            if (pos == -1) {
                sb.append(lletra);
            } else {
                char original = MAJUSCULES[pos];
                sb.append(esMinuscula ? Character.toLowerCase(original) : original);
            }
        }
        return sb.toString();
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(seed);
        return new TextXifrat(xifraPoliAlfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(seed);
        return desxifraPoliAlfa(xifrat.toString());
    }
}