/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.academica.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

/**
 *
 * @author OFlorez
 */
public class DESEncryption {

    /**
     * Encripta utilizando el algoritmo MD5
     */
    public String encrypMD5(String texto) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return hex(md5.digest(texto.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public String hex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    /**
     * Encripta un String utilizando el algoritmo DES
     *
     * @param clearText Texto en ckaro a encriptar
     * @return texto encriptado en base 64
     */
    public String encrypt(String clearText) {
        String secretKey = "~AutodeclaracionesFinal*"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = clearText.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    /**
     * Desencripta un testo cifrado en DES i codificado en base 64
     *
     * @param String cipherTextB64 Testo cifrado en DES y codificado en B64
     * @return String Texto en claro
     */
    public String decrypt(String cipherTextB64) {
        String secretKey = "~AutodeclaracionesFinal*"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(cipherTextB64.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
}
