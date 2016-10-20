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
