package iticbcn.xifratge;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.util.Arrays;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    public static final int MIDA_IV = 16;

    public byte[] generaIv() {
        byte[] ivBytes = new byte[MIDA_IV];
        new SecureRandom().nextBytes(ivBytes);
        return ivBytes;
    }

    private SecretKeySpec generaHash(String clau) throws Exception {
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(clau.getBytes("UTF-8"));
        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }

    public byte[] extreureIv(byte[] bIvIMsgXifrat) {
        return Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
    }

    public byte[] getBytesXifrats(byte[] bIvIMsgXifrat) {
        return Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);
    }

    public byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] bMsg = msg.getBytes("UTF-8");

        byte[] ivBytes = generaIv();
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec keySpec = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] bXifrat = cipher.doFinal(bMsg);

        byte[] resultat = new byte[MIDA_IV + bXifrat.length];
        System.arraycopy(ivBytes, 0, resultat, 0, MIDA_IV);
        System.arraycopy(bXifrat, 0, resultat, MIDA_IV, bXifrat.length);
        return resultat;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        byte[] ivBytes = extreureIv(bIvIMsgXifrat);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        byte[] bXifrat = getBytesXifrats(bIvIMsgXifrat);
        SecretKeySpec keySpec = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] bDesxifrat = cipher.doFinal(bXifrat);
        return new String(bDesxifrat, "UTF-8");
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            return new TextXifrat(xifraAES(msg, clau));
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            System.err.println("Error de desxifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }
}