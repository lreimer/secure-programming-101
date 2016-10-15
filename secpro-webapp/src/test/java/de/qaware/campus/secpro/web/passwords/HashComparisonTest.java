package de.qaware.campus.secpro.web.passwords;

import de.qaware.commons.crypto.*;
import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.Test;

import java.security.MessageDigest;

/**
 * Simple test to check different password hashing algorithms.
 */
public class HashComparisonTest {

    private static final String MASTER_PASSWORD = "1qay2wsx";
    private static final String PASSWORD = "SuperSecret";

    @Test
    public void md5() throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(PASSWORD.getBytes());
        String s = Base64.encodeBase64String(digest);
        System.out.format("MD5 Hash: %s%n", s);
    }

    @Test
    public void sha1() throws Exception {
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        byte[] digest = sha1.digest(PASSWORD.getBytes());
        String s = Base64.encodeBase64String(digest);
        System.out.format("SHA1 Hash: %s%n", s);
    }

    @Test
    public void heimdall() throws Exception {
        String saltBase64 = Base64.encodeBase64String(new byte[]{'s', 'a', 'l', 't'});
        Salt salt = Salt.fromBase64(saltBase64);
        Key key = Key.fromPassword(MASTER_PASSWORD.toCharArray(), salt, 42);

        Plaintext plaintext = Plaintext.fromString(PASSWORD);
        Ciphertext ciphertextIn = CryptoUtil.encrypt(key, plaintext);
        String ciphertextInAsString = ciphertextIn.toBase64();
        System.out.format("Heimdall: %s%n", ciphertextInAsString);
    }
}
