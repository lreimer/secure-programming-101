/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 QAware GmbH, Munich, Germany
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.qaware.campus.secpro.web.passwords;

import de.qaware.commons.crypto.*;
import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Simple unit test to check the secure passwords component.
 *
 * @author mario-leander.reimer
 */
public class SecurePasswordsTest {

    private static final String MASTER_PASSWORD = "1qay2wsx";
    private static final String MY_SUPER_SECURE_PASSWORD = "MySuperSecurePassword";

    private Salt salt;
    private SecurePasswords securePasswords;

    @BeforeMethod
    public void setUp() throws Exception {
        String saltBase64 = Base64.encodeBase64String(new byte[]{'s', 'a', 'l', 't'});
        salt = Salt.fromBase64(saltBase64);

        System.setProperty("de.qaware.campus.secpro.password", MASTER_PASSWORD);
        securePasswords = new SecurePasswords(MasterPassword.INSTANCE);
        securePasswords.initialize();
    }

    @Test
    public void testEncryptDycrypt() {
        String encrypted = securePasswords.encrypt(MY_SUPER_SECURE_PASSWORD);
        assertNotNull(encrypted);

        String decrypted = securePasswords.decrypt(encrypted);
        assertEquals(decrypted, MY_SUPER_SECURE_PASSWORD);
    }

    @Test
    public void testMasterEncryptionDecryption() throws Exception {
        Key key = Key.fromPassword(MASTER_PASSWORD.toCharArray(), salt, 42);

        // encrypt the super secure password
        Plaintext plaintext = Plaintext.fromString(MY_SUPER_SECURE_PASSWORD);
        Ciphertext ciphertextIn = CryptoUtil.encrypt(key, plaintext);
        String ciphertextInAsString = ciphertextIn.toBase64();
        assertNotNull(ciphertextInAsString);

        // Use fromBase64() to decode a ciphertext from Base64
        Ciphertext ciphertextOut = Ciphertext.fromBase64(ciphertextInAsString);
        Plaintext decoded = CryptoUtil.decrypt(key, ciphertextOut);
        String decrypted = decoded.asUtf8String();
        assertEquals(decrypted, MY_SUPER_SECURE_PASSWORD);
    }

    @Test
    public void testPasswordEncryptionDecryption() throws Exception {
        Key key = Key.fromPassword(MASTER_PASSWORD.toCharArray(), salt, 42);

        // encrypt the super secure password
        Plaintext plaintext = Plaintext.fromString("password");
        Ciphertext ciphertextIn = CryptoUtil.encrypt(key, plaintext);
        String ciphertextInAsString = ciphertextIn.toBase64();
        assertNotNull(ciphertextInAsString);

        // Use fromBase64() to decode a ciphertext from Base64
        Ciphertext ciphertextOut = Ciphertext.fromBase64(ciphertextInAsString);
        Plaintext decoded = CryptoUtil.decrypt(key, ciphertextOut);
        String decrypted = decoded.asUtf8String();
        assertEquals(decrypted, "password");
    }
}