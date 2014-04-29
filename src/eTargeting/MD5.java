package eTargeting;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
/**
 * <b>The MD5 class is used to encrypt into MD5 a string..</b>
 * <p>
 * It is mostly used for password's encryption.
 * </p>
 * 
 * @author Axel
 * @version 1.0
 */
public class MD5 {
	/**
	 * Get the MD5 result 
	 * @param input
	 * @return
	 */
    public static String getMD5(String input) {
        try {
            MessageDigest md     = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number    = new BigInteger(1, messageDigest);
            String hashtext      = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}