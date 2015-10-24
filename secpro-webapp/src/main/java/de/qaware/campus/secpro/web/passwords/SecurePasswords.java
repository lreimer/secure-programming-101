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

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * A simple class to encrypt and decrypt secure passwords using the master
 * password managed by the Glassfish password alias.
 *
 * @author mario-leander.reimer
 */
@ApplicationScoped
public class SecurePasswords {

    private static final int ITERATIONS = 42;

    @Inject
    private MasterPassword masterPassword;
    private Salt salt;

    /**
     * Default constructor.
     */
    public SecurePasswords() {
    }

    /**
     * Manually inject the master password instance.
     *
     * @param masterPassword the master password
     */
    SecurePasswords(MasterPassword masterPassword) {
        this.masterPassword = masterPassword;
    }

    @PostConstruct
    public void initialize() {
        String saltBase64 = Base64.encodeBase64String(new byte[]{'s', 'a', 'l', 't'});
        salt = Salt.fromBase64(saltBase64);
    }

    /**
     * Encrypt the given plaintext string using the master password.
     *
     * @param plaintext the plain text password
     * @return the Base64 encoded encrypted password.
     */
    public String encrypt(String plaintext) {
        try {
            Key key = getKey();
            Ciphertext ciphertext = CryptoUtil.encrypt(key, Plaintext.fromString(plaintext));
            return ciphertext.toBase64();
        } catch (CryptoException e) {
            throw new SecurityException(e);
        }
    }

    /**
     * Decrypt the given ciphertext string into the plaintext original.
     *
     * @param ciphertext the ciphertext
     * @return the decrypted string
     */
    public String decrypt(String ciphertext) {
        try {
            Key key = getKey();
            Plaintext decoded = CryptoUtil.decrypt(key, Ciphertext.fromBase64(ciphertext));
            return decoded.asUtf8String();
        } catch (CryptoException e) {
            throw new SecurityException(e);
        }
    }

    private Key getKey() throws CryptoException {
        char[] password = masterPassword.toString().toCharArray();
        return Key.fromPassword(password, salt, ITERATIONS);
    }
}
