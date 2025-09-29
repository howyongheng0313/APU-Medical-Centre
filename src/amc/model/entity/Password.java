package amc.model.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class Password {
    private static final MessageDigest SHA256;
    private final byte[] hash;
    private final byte[] salt;

    static {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {}
        SHA256 = md;
    }

    public Password(byte[] hash, byte[] salt) {
        this.hash = hash;
        this.salt = salt;
    }

    public Password(String hash64, String salt64) {
        this.hash = Base64.getDecoder().decode(hash64);
        this.salt = Base64.getDecoder().decode(salt64);
    }

    // Core Function (pass + salt -> hash);
    private static byte[] hashing(String password, byte[] salt) {
        SHA256.reset();
        SHA256.update(salt);
        byte[] hash = SHA256.digest(password.getBytes());
        return hash;
    }

    // New Password Function random_salt(); (pass + ranSalt -> newHash)
    public static Password build(String password) {
        byte[] ranSalt = new byte[12];
        new Random().nextBytes(ranSalt);
        byte[] newHash = Password.hashing(password, ranSalt);
        return new Password(newHash, ranSalt);
    }

    // Login Function (x + salt -> xhash); compare(hash, xhash);
    public boolean verify(String password) {
        byte[] hashCp = Password.hashing(password, this.salt);
        return Arrays.equals(this.hash, hashCp);
    }

    public String getHash64() { return Base64.getEncoder().encodeToString(this.hash); }
    public String getSalt64() { return Base64.getEncoder().encodeToString(this.salt); }
    public byte[] getHash() { return hash; }
    public byte[] getSalt() { return salt; }
}
