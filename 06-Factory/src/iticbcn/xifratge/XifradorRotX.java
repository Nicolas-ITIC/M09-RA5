package iticbcn.xifratge;
public class XifradorRotX implements Xifrador {
 
    public static char[] minuscules = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz".toCharArray();
    public static char[] majuscules = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    
    // Utilitzem els mateixos metodes substituint el 13 per la variable desplaçament
    public char xifraChar(char c, int desplaçament) {
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
 
    public char desxifraChar(char c, int desplaçament) {
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
 
    public String xifraRotX(String text, int desplaçament) {
        String resultat = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            resultat = resultat + xifraChar(c, desplaçament);
        }
        return resultat;
    }
 
    public String desxifraRotX(String text, int desplaçament) {
        String resultat = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            resultat = resultat + desxifraChar(c, desplaçament);
        }
        return resultat;
    }
    
    private int llegeixClau(String clau) throws ClauNoSuportada {
        String error = "Clau de RotX ha de ser un sencer de 0 a 40";
        try {
            int n = Integer.parseInt(clau);
            if (n < 0 || n > 40) throw new ClauNoSuportada(error);
            return n;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada(error);
        }
    }

    public void forcaBrutaRotX(String cadenaXifrada) {
        System.out.println("Missatge xifrat: " + cadenaXifrada);
        System.out.println("----------------");
        for (int desplaçament = 0; desplaçament < majuscules.length; desplaçament++) {
            String desxifrat = desxifraRotX(cadenaXifrada, desplaçament);
            System.out.println("(" + desplaçament + ")-> " + desxifrat);
        }
    }
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desp = llegeixClau(clau);
        return new TextXifrat(xifraRotX(msg, desp).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desp = llegeixClau(clau);
        return desxifraRotX(xifrat.toString(), desp);
    }
}
 
