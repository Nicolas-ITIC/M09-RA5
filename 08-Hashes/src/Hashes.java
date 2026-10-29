import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(pw.getBytes());

        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 10000, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] bytes = skf.generateSecret(spec).getEncoded();

        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String forcaBruta(String alg, String hash, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String charset = "abcdefABCDEF1234567890!";
        int maxLong = 6;
        npass = 0;

        for (int len = 1; len <= maxLong; len++) {
            char[] password = new char[len];
            String resultat = provarLongitud(password, 0, len, charset, alg, hash, salt);
            if (resultat != null) {
                return resultat;
            }
        }
        return null;
    }

    private String provarLongitud(char[] password, int pos, int len, String charset,
                                   String alg, String hash, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (pos == len) {
            String pwActual = new String(password);
            npass++;

            String hashActual;
            if (alg.equals("SHA-512")) {
                hashActual = getSHA512AmbSalt(pwActual, salt);
            } else {
                hashActual = getPBKDF2AmbSalt(pwActual, salt);
            }

            if (hashActual.equals(hash)) {
                return pwActual;
            }
            return null;
        }

        for (int i = 0; i < charset.length(); i++) {
            password[pos] = charset.charAt(i);
            String resultat = provarLongitud(password, pos + 1, len, charset, alg, hash, salt);
            if (resultat != null) {
                return resultat;
            }
        }
        return null;
    }

    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;

        long millis = diff % 1000;
        diff /= 1000;
        long segons = diff % 60;
        diff /= 60;
        long minuts = diff % 60;
        diff /= 60;
        long hores = diff % 24;
        diff /= 24;
        long dies = diff;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                dies, hores, minuts, segons, millis);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}