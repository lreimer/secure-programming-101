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
package de.qaware.campus.secpro.env01;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

/**
 * A first try at a certificate checker. It will extract the certificates
 * from the code source and verify this against a key store.
 */
public class CertificateVerifier {

    /**
     * Verify the signature of the given class.
     *
     * @param clazz the clazz
     * @throws SecurityException if the verification fails
     */
    public void verify(Class<?> clazz) throws SecurityException {
        Certificate[] certificates = clazz.getProtectionDomain().getCodeSource().getCertificates();
        if (certificates == null || certificates.length == 0) {
            System.out.println("No certificates found. Code source is not signed.");
            return;
        }

        try {
            KeyStore ks = KeyStore.getInstance("JKS");
            BufferedInputStream inputStream = new BufferedInputStream(clazz.getResourceAsStream("/keystore.jks"));

            ks.load(inputStream, "password".toCharArray());
            boolean verified = certificates[0].equals(ks.getCertificate("selfsigned"));
            if (!verified) {
                throw new SecurityException("The signer certificate could not be verified.");
            }
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new SecurityException(e);
        }
    }
}
