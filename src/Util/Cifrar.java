package Util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author CPZ
 */
public class Cifrar {

    public static String cifrarCadena(String modo, String cadena, String llave) {
        SecretKeySpec key;
        switch (modo) {
            case "AES": {
                try {
                    key = new SecretKeySpec(llave.getBytes("UTF-8"), modo);
                    Cipher cipher = Cipher.getInstance(modo);
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    byte[] cadenaCifrada = cipher.doFinal(cadena.getBytes("UTF-8"));
                    return Base64.getEncoder().encodeToString(cadenaCifrada);
                } catch (NoSuchAlgorithmException
                        | NoSuchPaddingException
                        | InvalidKeyException
                        | IllegalBlockSizeException
                        | BadPaddingException
                        | UnsupportedEncodingException e) {
                    System.out.println(e.getMessage());
                }
            }
            default:
                return "";
        }
    }

    public static String descifrarCadena(String modo, String cadenaCifrada64, String llave) {
        switch (modo) {
            case "AES":
                try {
                    SecretKeySpec key = new SecretKeySpec(llave.getBytes("UTF-8"), modo);
                    Cipher cipher = Cipher.getInstance(modo);
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    byte[] cadenaCifrada = Base64.getDecoder().decode(cadenaCifrada64.trim());
                    byte[] descifrado = cipher.doFinal(cadenaCifrada);
                    return new String(descifrado, "UTF-8");
                } catch (NoSuchAlgorithmException
                        | NoSuchPaddingException
                        | InvalidKeyException
                        | IllegalBlockSizeException
                        | UnsupportedEncodingException
                        | BadPaddingException e) {
                    System.out.println(e.getMessage());
                }
            default:
                return "";
        }
    }

}
