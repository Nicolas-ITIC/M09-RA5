public class Rot13 {
    public static char[] minuscules = "aàábcçdeèéfhiíìïjlmnñoòópqrstuúùüvwxyz".toCharArray();
    public static char[] majuscules = "AÀÁBCÇDEÈÉFGHIÍÌÏJKLMNÑOÒÓPQRSTUÚÙÜVWXYZ".toCharArray();

    public static char xifraChar(char c) {
        if (Character.isLetter(c)){
            if (Character.isLowerCase(c)) {
                for (int i = 0; i < minuscules.length; i++) {
                    if (c==minuscules[i]) {
                        int posicio = i + 13;
                        if (posicio >= minuscules.length) {
                            posicio = posicio - minuscules.length;
                        }
                        return minuscules[posicio];
                    }
                }
            }
            if (Character.isUpperCase(c)) {
                for (int i = 0; i < majuscules.length; i++) {
                    if (c==majuscules[i]) {
                        int posicio = i + 13;
                        if (posicio >= majuscules.length) {
                            posicio = posicio - majuscules.length;
                        }
                        return majuscules[posicio];
                    }
                }
            }
        }
        return c;
    }
    public static char desxifraChar(char c) {
        if (Character.isLetter(c)){
            if (Character.isLowerCase(c)) {
                for (int i = 0; i < minuscules.length; i++) {
                    if (c==minuscules[i]) {
                        int posicio = i - 13;
                        if (posicio < 0) {
                            posicio = posicio + minuscules.length;
                        }
                        return minuscules[posicio];
                    }
                }
            }
            if (Character.isUpperCase(c)) {
                for (int i = 0; i < majuscules.length; i++) {
                    if (c==majuscules[i]) {
                        int posicio = i - 13;
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
    public static String xifraRot13(String text) {
        String resultat = "";
        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            resultat = resultat + xifraChar(c);
        }
        return resultat;
    }
    public static String desxifraRot13(String text) {
        String resultat = "";
        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            resultat = resultat + desxifraChar(c);
        }
        return resultat;
    }
    public static void main(String[] args) {
        System.out.println("Xifrat");
        System.out.println("---------");
        System.out.println("ABC => " + xifraRot13("ABC"));
        System.out.println("XYZ => " + xifraRot13("XYZ"));
        System.out.println("Hola, Mr. calçot => " + xifraRot13("Hola, Mr. calçot"));
        System.out.println("Perdó, per tu què és? => " + xifraRot13("Perdó, per tu què és?"));

        System.out.println("\nDesxifrat");
        System.out.println("---------");
        System.out.println("IÏJ => " + desxifraRot13("IÏJ"));
        System.out.println("FGH => " + desxifraRot13("FGH"));
        System.out.println("Ówúí, Ùà. líúmwb => " + desxifraRot13("Ówúí, Ùà. líúmwb"));
        System.out.println("Zñàny, zñà bc aco òá? => " + desxifraRot13("Zñàny, zñà bc aco òá?"));
    }
}