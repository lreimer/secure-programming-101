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

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The passwords JSF model bean.
 *
 * @author mario-leander.reimer
 */
@Model
public class Passwords {

    private static final Logger LOGGER = Logger.getLogger(Passwords.class.getName());

    @Inject
    private MasterPassword masterPassword;

    @Inject
    private SecurePasswords securePasswords;

    @Inject
    private CertificateVerifier verifier;

    public String getMasterPassword() {
        return Objects.toString(masterPassword);
    }

    public String getSecurePassword() {
        return securePasswords.getDecryptedSecurePassword();
    }

    public boolean isCertificateValid() {
        boolean valid;
        try {
            verifier.verify(getClass());
            valid = true;
        } catch (SecurityException e) {
            LOGGER.log(Level.WARNING, "Error validating certificate.", e);
            valid = false;
        }
        return valid;
    }
}
