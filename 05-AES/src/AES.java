import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.util.Arrays;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    public static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LGINNUUX6967";

    public static byte[] generaIv() {
        byte[] ivBytes = new byte[MIDA_IV];
        new SecureRandom().nextBytes(ivBytes);
        return ivBytes;
    }

    private static SecretKeySpec generaHash(String clau) throws Exception {
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(clau.getBytes("UTF-8"));
        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }

    public static byte[] extreureIv(byte[] bIvIMsgXifrat) {
        return Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
    }

    public static byte[] getBytesXifrats(byte[] bIvIMsgXifrat) {
        return Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] bMsg = msg.getBytes("UTF-8");

        byte[] ivBytes = generaIv();
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        SecretKeySpec keySpec = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] bXifrat = cipher.doFinal(bMsg);

        // Combinar IV i part xifrada
        byte[] resultat = new byte[MIDA_IV + bXifrat.length];
        System.arraycopy(ivBytes, 0, resultat, 0, MIDA_IV);
        System.arraycopy(bXifrat, 0, resultat, MIDA_IV, bXifrat.length);

        return resultat;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        byte[] ivBytes = extreureIv(bIvIMsgXifrat);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // Extreure la part xifrada
        byte[] bXifrat = getBytesXifrats(bIvIMsgXifrat);

        // Fer hash de la clau
        SecretKeySpec keySpec = generaHash(clau);

        // Desxifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] bDesxifrat = cipher.doFinal(bXifrat);
        
        return new String(bDesxifrat, "UTF-8");
    }

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                         "Hola Andrés cómo está tu cuñado",
                         "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                    + e.getLocalizedMessage());
            }
            System.out.println("---------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}


