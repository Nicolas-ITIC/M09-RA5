import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.util.Arrays;


public class AES {
    
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256"
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding"
    public static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LGINNUUX6967"

    /*
    METODES A IMPLEMENTAR:
    extreureIv
    getBytesXifrats
    generaHash -> Torna SecretKeySpec

    EN PROCES
    getBytesXifrats

    FET
    generaIV

    IMPORTANT:
    Passar string a array de bytes y aquest a string NO es simetric
    */

    public static byte [] generaIv() {
        byte[] ivBytes = new byte[MIDA_IV];
        new SecureRandom().nextBytes(ivBytes);
        return ivBytes;
    }

    public static byte [] getBytesXifrats (String msg) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] msgByte = msg.getBytes()


    }

    public static byte[] xifraAES (String msg, String password) {
        

        
        SecureRandom secureRandom = new SecureRandom();

    }

    public static String desxifraAES (byte[] bMsgXifrat, String password) {

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
        }
        System.out.println("---------------------");
        System.out.println("Msg: " + msg);
        System.out.println("Enc: " + new String(bXifrats));
        System.out.println("DEC: " + desxifrat);
    }
}


