/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hashTest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

/**
 *
 * @author kzy
 */
public class PassHash {
    public final String hash64;
    public final String salt64;

    private PassHash(String hash64, String salt64) {
        this.hash64 = hash64;
        this.salt64 = salt64;
    }

    public static PassHash hashing(String password) {
        byte[]  ranSalt = new byte[12];
        new Random().nextBytes(ranSalt);

        String hash64 = PassHash.findHash64(password, ranSalt);
        String salt64 = Base64.getEncoder().encodeToString(ranSalt);
        return new PassHash(hash64, salt64);
    }

    private static String findHash64(String password, byte[] saltLs) {
        MessageDigest sha256;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return "";
        }

        sha256.update(saltLs);
        byte[] hashLs = sha256.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashLs);
    }

    public static boolean checkPass(String password, String hash64, String salt64) {
        byte[] saltLs = Base64.getDecoder().decode(salt64);
        String hashCp = PassHash.findHash64(password, saltLs);
        return hashCp.equals(hash64);
    }
}
