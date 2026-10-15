package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {

    public static final char[] MAJUSCULES = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private char[] permutat;

    public XifradorMonoalfabetic() {
        permutat = permutaAlfabet(MAJUSCULES);
    }

    public char[] permutaAlfabet(char[] alfabet) {
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

    public String xifraMonoAlfa(String cadena) {
        char[] original = cadena.toCharArray();
        char[] sortida = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            char c = original[i];
            int index = indexOf(MAJUSCULES, Character.toUpperCase(c));
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

    public String desxifraMonoAlfa(String cadena) {
        char[] xifrada = cadena.toCharArray();
        char[] sortida = new char[xifrada.length];
        for (int i = 0; i < xifrada.length; i++) {
            char c = xifrada[i];
            int index = indexOf(permutat, Character.toUpperCase(c));
            if (index == -1) {
                sortida[i] = c;
            } else {
                char original = MAJUSCULES[index];
                if (Character.isLowerCase(c)) {
                    original = Character.toLowerCase(original);
                }
                sortida[i] = original;
            }
        }
        return new String(sortida);
    }

    private int indexOf(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) return i;
        }
        return -1;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        return new TextXifrat(xifraMonoAlfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        return desxifraMonoAlfa(xifrat.toString());
    }
}